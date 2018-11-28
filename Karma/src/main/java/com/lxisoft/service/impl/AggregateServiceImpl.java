/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lxisoft.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Category;
import com.lxisoft.domain.Comment;
import com.lxisoft.domain.Feed;
import com.lxisoft.domain.Help;
import com.lxisoft.domain.Need;
import com.lxisoft.domain.Post;
import com.lxisoft.domain.RegisteredUser;
import com.lxisoft.domain.Reply;
import com.lxisoft.domain.UserCheck;
import com.lxisoft.repository.ApprovalStatusRepository;
import com.lxisoft.repository.CategoryRepository;
import com.lxisoft.repository.CommentRepository;
import com.lxisoft.repository.FeedRepository;
import com.lxisoft.repository.HelpRepository;
import com.lxisoft.repository.NeedRepository;
import com.lxisoft.repository.PostRepository;
import com.lxisoft.repository.RegisteredUserRepository;
import com.lxisoft.repository.ReplyRepository;
import com.lxisoft.repository.SeverityRepository;
import com.lxisoft.repository.UserCheckRepository;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.service.dto.FeedDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.PostDTO;
import com.lxisoft.service.dto.ReplyDTO;
import com.lxisoft.service.dto.SeverityDTO;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.service.mapper.ApprovalStatusMapper;
import com.lxisoft.service.mapper.CategoryMapper;
import com.lxisoft.service.mapper.CommentMapper;
import com.lxisoft.service.mapper.FeedMapper;
import com.lxisoft.service.mapper.HelpMapper;
import com.lxisoft.service.mapper.NeedMapper;
import com.lxisoft.service.mapper.PostMapper;
import com.lxisoft.service.mapper.RegisteredUserMapper;
import com.lxisoft.service.mapper.ReplyMapper;
import com.lxisoft.service.mapper.SeverityMapper;
import com.lxisoft.service.mapper.UserCheckMapper;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

/**
 * Service Implementation for managing all domains.
 */
@Service
@Transactional
public class AggregateServiceImpl implements AggregateService {

	private final Logger log = LoggerFactory.getLogger(AggregateServiceImpl.class);

	@Autowired
	NeedRepository needRepository;

	@Autowired
	ApprovalStatusRepository approvalStatusRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserCheckRepository userCheckRepository;

	@Autowired
	SeverityRepository severityRepository;

	@Autowired
	HelpRepository helpRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	FeedRepository feedRepository;

	@Autowired
	RegisteredUserRepository registeredUserRepository;

	@Autowired
	NeedMapper needMapper;

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
	ApprovalStatusMapper approvalStatusMapper;

	@Autowired
	UserCheckMapper userCheckMapper;

	@Autowired
	HelpMapper helpMapper;

	@Autowired
	CommentMapper commentMapper;

	@Autowired
	ReplyMapper replyMapper;

	@Autowired
	SeverityMapper severityMapper;

	@Autowired
	RegisteredUserMapper registeredUserMapper;

	@Autowired
	FeedMapper feedMapper;

	@Autowired
	PostMapper postMapper;

	@Autowired
	private JavaMailSender sender;

	/**
	 * Save a need.
	 *
	 * @param needDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws IOException
	 */
	@Override
	public NeedDTO saveNeed(NeedDTO needDTO) throws IOException {
		log.debug("Request to save Need : {}", needDTO);

		Need oldNeed = needRepository.getOne(needDTO.getId());

		NeedDTO newNeed = needMapper.toDto(oldNeed);

		newNeed.setId(needDTO.getId());
		newNeed.setApprovalStatusId(needDTO.getApprovalStatusId());

		Need need = needMapper.toEntity(newNeed);

		need = needRepository.save(need);

		// anjali
		FeedDTO feedDto = new FeedDTO();

		feedDto.setType("NeedPostAfterApproval");
		feedDto.setReferenceId(need.getId());
		feedDto.setRegisteredUserId(need.getPostedUser().getId());

		saveFeed(feedDto);
		// anjali

		return needMapper.toDto(need);
	}

	/**
	 * Save a need.
	 *
	 * @param needDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws IOException
	 */
	@Override
	public NeedDTO saveNeedAsPending(NeedDTO needDTO) throws IOException {
		log.debug("Request to save Need : {}", needDTO);

		Set<CategoryDTO> categorySet = new HashSet<CategoryDTO>();

		if (needDTO.getApprovalStatusId() == null) {

			Optional<ApprovalStatus> approvalStatus = approvalStatusRepository.findByStatus("pending");

			Long id = approvalStatus.get().getId();
			log.debug("***************{}" + id);
			needDTO.setApprovalStatusId(approvalStatus.get().getId());
		}

		needDTO.setCategories(new HashSet<CategoryDTO>(needDTO.getCategoryList()));

		Set<CategoryDTO> categories = needDTO.getCategories();

		// System.out.println(categories);

		for (CategoryDTO categori : categories) {
			Long id = categori.getId();
			Category categorie = categoryRepository.findById(id).orElse(null);
			CategoryDTO categoree = categoryMapper.toDto(categorie);
			categorySet.add(categoree);
		}
		needDTO.setCategories(categorySet);

		// parsing string to ISO_INSTANT format
		String parsedDate = needDTO.getDateInString().replaceAll(" ", "T").concat("Z");

		// creates a date instance of type instant from a string
		needDTO.setDate(Instant.parse(parsedDate));

		Need need = needMapper.toEntity(needDTO);
		need = needRepository.save(need);

		log.info("need id{}", need.getId());

		return needMapper.toDto(need);
	}

	/**
	 * Get all the needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<NeedDTO> findAllNeeds(Pageable pageable) {
		log.debug("Request to get all Needs");
		return needRepository.findAll(pageable).map(needMapper::toDto);
	}

	/**
	 * Get all the Need with eager load of many-to-many relationships.
	 *
	 * @return the list of entities
	 */
	@Override
	public Page<NeedDTO> findAllWithEagerRelationships(Pageable pageable) {
		return needRepository.findAllWithEagerRelationships(pageable).map(needMapper::toDto);
	}

	/**
	 * Get one need by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<NeedDTO> findOneNeed(Long id) {
		log.debug("Request to get Need : {}", id);
		return needRepository.findOneWithEagerRelationships(id).map(needMapper::toDto);
	}

	/**
	 * Delete the need by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void deleteNeed(Long id) {
		log.debug("Request to delete Need : {}", id);
		needRepository.deleteById(id);
	}

	/**
	 * Get all the approvedstatus needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of approvedstatus entities
	 */
	@Override
	public Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus) {

		log.debug("Request to get all Needs by approval status");

		long approvalStatusId = findNeedByApprovalStatus(approvalStatus).get().getId();

		Page<NeedDTO> page = findAllNeedsByApprovalStatusId(pageable, approvalStatusId);

		List<NeedDTO> needs = page.getContent();

		int count = 0;

		for (NeedDTO need : needs) {

			log.info("*****************{}", need.getId());

			Page<UserCheckDTO> userCheckDTOs = findAllUserChecksByCheckedNeedId(pageable, need.getId());

			List<UserCheckDTO> userCheckDTOList = userCheckDTOs.getContent();

			if (userCheckDTOList.size() == 0) {
				need.setPercentageOfGenuineness(null);

			} else {

				float genuinescount = calculateNoOfGenuiness(need.getId());

				need.setPercentageOfGenuineness((long) ((genuinescount / userCheckDTOList.size()) * 100));

				// need.setPercentageOfGenuineness(genuinescount);

			}

			need.setNoOfComments((long) (commentRepository.countByNeedId(need.getId())));

			need.setNoOfHelps((long) (helpRepository.countOfHelpsByfulfilledNeedId(need.getId(), "completed")));

			need.setCategoryList(new ArrayList<CategoryDTO>(need.getCategories()));
			SeverityDTO severityDTO = null;

			if (need.getSeverityId() != null)
				severityDTO = findOneseverity(need.getSeverityId()).orElse(null);

			if (severityDTO != null)
				need.setSeverityLevel(severityDTO.getSeverityLevel());

			Instant instant = Instant.now();

			Date repliedTime = null;
			if (need.getDate() != null) {
				repliedTime = Date.from(need.getDate());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (repliedTime != null) {
				diffInSecond = (current.getTime() - repliedTime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				need.setTimeElapsed(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				need.setTimeElapsed(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				need.setTimeElapsed(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				need.setTimeElapsed(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				need.setTimeElapsed(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				need.setTimeElapsed(postedBefore + " years ago");
			}
			System.out.println("how many ago " + need.getTimeElapsed());

		}

		Page<NeedDTO> pagee = new PageImpl<NeedDTO>(needs, pageable, needs.size());

		return pagee;
	}

	/**
	 * Get all the helpsByfulfilledNeedId.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param approvedStatus
	 *            the approvedStatus of the entity
	 * @return the list of entities
	 */
	@Override
	public Page<HelpDTO> findAllHelpsByfulfilledNeedId(Pageable pageable, Long fulfilledNeedId) {

		log.debug("Request to get all Needs by approval status id{}", fulfilledNeedId);

		return helpRepository.findAllHelpsByfulfilledNeedId(pageable, fulfilledNeedId).map(helpMapper::toDto);
	}

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */
	@Override
	public Integer calculateLikesNumberOfHelps(Long checkedHelpId) {
		return userCheckRepository.countOfVoteTypeLike("positive", checkedHelpId);
	}

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */
	@Override
	public Integer calculateNoOfGenuiness(Long checkedNeedId) {
		return userCheckRepository.countOfVoteTypeGenuiness("postive", checkedNeedId);
	}

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */

	@Override
	public Integer calculateDislikesNumberOfHelps(Long checkedHelpId) {
		return userCheckRepository.countOfVoteTypeLike("negative", checkedHelpId);
	}

	/**
	 * Get all the approvedstatus needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of approvedstatus entities
	 */
	@Override
	public Page<NeedDTO> findAllNeedsByApprovalStatusId(Pageable pageable, Long approvalStatusId) {

		log.debug("Request to get all Needs by approval status id{}", approvalStatusId);

		return needRepository.findAllNeedsByApprovalStatusId(pageable, approvalStatusId).map(needMapper::toDto);

	}

	/**
	 * Get all the SeverityId needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of entities
	 */
	@Override
	public Page<NeedDTO> findAllNeedsBySeverityId(Pageable pageable, Long severityId) {

		log.debug("Request to get all Needs by severity id{}", severityId);

		return needRepository.findAllNeedsBySeverityId(pageable, severityId).map(needMapper::toDto);

	}

	/**
	 * get need by approvalstatus
	 *
	 * @param status
	 *            the status of the entity
	 */
	@Override
	public Optional<ApprovalStatusDTO> findNeedByApprovalStatus(String approvalStatus) {
		log.debug("Request to retreive pending status: {}", approvalStatus);
		return approvalStatusRepository.findByStatus(approvalStatus).map(approvalStatusMapper::toDto);

	}

	/**
	 * Get all the userChecks by checkedNeedId.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of entities
	 */
	@Override
	public Page<UserCheckDTO> findAllUserChecksByCheckedNeedId(Pageable pageable, Long checkedNeedId) {

		log.debug("Request to get all UserChecks");
		return userCheckRepository.findAllUserChecksByCheckedNeedId(pageable, checkedNeedId)
				.map(userCheckMapper::toDto);
	}

	/**
	 * Get all the approvalStatuses.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<ApprovalStatusDTO> findAllApprovalStatuses(Pageable pageable) {
		log.debug("Request to get all ApprovalStatuses");
		return approvalStatusRepository.findAll(pageable).map(approvalStatusMapper::toDto);
	}

	/**
	 * Get one approvalStatus by id.
	 *
	 * @param id
	 *            the id of the entity
	 * 
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ApprovalStatusDTO> findOneApprovalStatus(Long id) {
		log.debug("Request to get ApprovalStatus : {}", id);
		return approvalStatusRepository.findById(id).map(approvalStatusMapper::toDto);
	}

	/**
	 * Get all the categories.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of entities
	 */
	@Override
	public Page<CategoryDTO> findAllCategories(Pageable pageable) {
		log.debug("Request to get all Categories");
		return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
	}

	/**
	 * Save a help.
	 *
	 * @param helpDTO
	 *            the entity to save
	 * 
	 * @return the persisted entity
	 * 
	 * @throws IOException
	 */
	@Override
	public HelpDTO saveHelpAsIncomplete(HelpDTO helpDTO) {
		log.debug("Request to save Help : {}", helpDTO);

		if (helpDTO.getApprovalStatusId() == null) {

			Optional<ApprovalStatus> approvalStatus = approvalStatusRepository.findByStatus("incompleted");

			Long id = approvalStatus.get().getId();
			log.debug("***************{}" + id);
			helpDTO.setApprovalStatusId(approvalStatus.get().getId());
		}

		String parsedDate = helpDTO.getTimeInString().replaceAll(" ", "T").concat("Z");

		// creates a date instance of type instant from a string
		helpDTO.setTime(Instant.parse(parsedDate));

		Help help = helpMapper.toEntity(helpDTO);
		help = helpRepository.save(help);
		return helpMapper.toDto(help);
	}

	/**
	 * Save a help.
	 *
	 * @param helpDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws IOException
	 */
	@Override
	public HelpDTO saveHelpAsComplete(HelpDTO helpDTO) throws IOException {
		log.debug("Request to save Help : {}", helpDTO);
		Help help = helpMapper.toEntity(helpDTO);
		help = helpRepository.save(help);

		// anjali

		FeedDTO feedDto = new FeedDTO();

		feedDto.setType("HelpPostAfterCompletion");
		feedDto.setReferenceId(help.getId());
		feedDto.setRegisteredUserId(help.getProvidedUser().getId());

		saveFeed(feedDto);

		// anjali

		return helpMapper.toDto(help);
	}

	/**
	 * Get all the helps.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<HelpDTO> findAllHelps(Pageable pageable) {
		log.debug("Request to get all Helps");
		return helpRepository.findAll(pageable).map(helpMapper::toDto);
	}

	/**
	 * Get one help by id.
	 *
	 * @param id
	 *            the id of the entity
	 * 
	 * @return the entity
	 */
	@Override
	public Optional<HelpDTO> findOneHelp(Long id) {
		log.debug("Request to get Help : {}", id);
		return helpRepository.findById(id).map(helpMapper::toDto);
	}

	/**
	 * Delete the help by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void deleteHelp(Long id) {
		log.debug("Request to delete Help : {}", id);
		helpRepository.deleteById(id);
	}

	/**
	 * Get all the helpsByApprovedStatus.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param approvedStatus
	 *            the approvedStatus of the entity
	 * @return the list of entities
	 */
	@Override
	public Page<HelpDTO> findAllHelpsByApprovedStatus(Pageable pageable, String approvalStatus) {

		log.debug("Request to get all Helps by approval status");

		Long approvalStatusId = approvalStatusRepository.findByStatus(approvalStatus).get().getId();

		Page<HelpDTO> helpDtos = helpRepository.findAllHelpsByApprovalStatusId(pageable, approvalStatusId)
				.map(helpMapper::toDto);

		List<HelpDTO> helps = helpDtos.getContent();
		for (HelpDTO help : helps) {
			Page<CommentDTO> commentsPage = findAllCommentsByHelpId(pageable, help.getId());
			if (commentsPage.getContent() == null)
				help.setNoOfComments((long) 0);
			else {
				List<CommentDTO> noOfComments = commentsPage.getContent();
				help.setNoOfComments((long) noOfComments.size());
			}
			help.setNoOfLikes((long) calculateLikesNumberOfHelps(help.getId()));
			help.setNoOfDisLikes((long) calculateDislikesNumberOfHelps(help.getId()));

			Instant instant = Instant.now();

			Date repliedTime = null;
			if (help.getTime() != null) {
				repliedTime = Date.from(help.getTime());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (repliedTime != null) {
				diffInSecond = (current.getTime() - repliedTime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				help.setTimeElapsed(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				help.setTimeElapsed(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				help.setTimeElapsed(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				help.setTimeElapsed(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				help.setTimeElapsed(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				help.setTimeElapsed(postedBefore + " years ago");
			}
			System.out.println("how many ago " + help.getTimeElapsed());

		}

		Page<HelpDTO> pagee = new PageImpl<HelpDTO>(helps, pageable, helps.size());

		return pagee;

	}

	/**
	 * Save a need with approval status.
	 *
	 * @param needDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws IOException
	 */
	@Override
	public NeedDTO saveNeedWithApprovalStatus(NeedDTO needDTO) throws IOException {
		log.debug("Request to save Need : {}", needDTO);
		Need need = needMapper.toEntity(needDTO);
		need = needRepository.save(need);
		String mail;
		if (need.getPostedUser() != null) {
			if (need.getPostedUser().getEmail() != null) {
				mail = need.getPostedUser().getEmail();
			} else {
				mail = "ruhailfatrhath@gmail.com";
			}
		} else {
			mail = "ruhailfarhath@gmail.com";
		}
		String result = null;
		if (need != null) {
			if (need.getApprovalStatus().getId() == 2) {
				sendMail(mail, "Your need is approved");
			} else if (need.getApprovalStatus().getId() == 3) {
				sendMail(mail, "Your need is declained");
			} else if (need.getApprovalStatus().getId() == 6) {
				sendMail(mail, "Your need is closed");
			}
			log.info("Mail sending status {}->", result);
		}

		return needMapper.toDto(need);
	}

	/**
	 * Send mail to posted user about approval status
	 *
	 * @param mail
	 *            the mail id to send mail
	 * @return the mail sending status
	 */
	@Override
	public String sendMail(String mail, String messageContent) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(mail);
			helper.setText(messageContent);
			helper.setSubject("Mail From Karma");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Success!";
	}

	/**
	 * Save a userCheck.
	 *
	 * @param userCheckDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public UserCheckDTO saveUserCheck(UserCheckDTO userCheckDTO) {
		log.debug("Request to save UserCheck : {}", userCheckDTO);
		UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
		userCheck = userCheckRepository.save(userCheck);
		return userCheckMapper.toDto(userCheck);
	}

	/**
	 * checking the genuineness.
	 *
	 * @param userCheckDTO
	 *            the userCheckDTO to create
	 * 
	 * @param voteType
	 *            the voteType of the userCheckDto
	 * 
	 * @return the object
	 * @throws IOException
	 */
	@Override
	public UserCheckDTO markingGenuinenes(UserCheckDTO userCheckDTO) throws IOException {

		UserCheck result = null;

		UserCheck usrCheckDtoObject = userCheckRepository
				.findUserCheckByCategoryAndCheckedNeedIdAndCheckedUserId(userCheckDTO.getCategory(),
						userCheckDTO.getCheckedNeedId(), userCheckDTO.getCheckedUserId())
				.orElse(null);

		if ((userCheckDTO.getIsGenuine() == true) && (usrCheckDtoObject == null)) {
			userCheckDTO.setVoteType("postive");
			UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
			result = userCheckRepository.save(userCheck);
		}

		else if ((userCheckDTO.getIsGenuine() == true) && (usrCheckDtoObject != null)) {
			usrCheckDtoObject.setVoteType("postive");
			result = userCheckRepository.save(usrCheckDtoObject);
		}

		else if ((userCheckDTO.getIsGenuine() == false) && (usrCheckDtoObject == null)) {
			userCheckDTO.setVoteType("negative");
			UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
			result = userCheckRepository.save(userCheck);
		}

		else {
			usrCheckDtoObject.setVoteType("negative");
			result = userCheckRepository.save(usrCheckDtoObject);
		}

		// anjali
		FeedDTO feedDto = new FeedDTO();

		if ((result.getCheckedNeed().getId() != null) && (userCheckDTO.getVoteType() == "postive")) {

			feedDto.setType("NeedIsGenuine");
			feedDto.setReferenceId(result.getCheckedNeed().getId());
			feedDto.setRegisteredUserId(result.getCheckedUser().getId());

		}

		if ((result.getCheckedNeed().getId() != null) && (userCheckDTO.getVoteType() == "negative")) {

			feedDto.setType("NeedIsFake");
			feedDto.setReferenceId(result.getCheckedNeed().getId());
			feedDto.setRegisteredUserId(result.getCheckedUser().getId());

			log.info("***{}inside", feedDto.getType());
		}
		log.info("***{}after", feedDto.getType());
		saveFeed(feedDto);

		// anjali

		return userCheckMapper.toDto(result);
	}

	/**
	 * Get all the userChecks by vote type.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of entities
	 * @throws IOException
	 */
	@Override
	public Optional<UserCheckDTO> saveUserCheckLike(UserCheckDTO userCheckDTO) throws IOException {
		log.debug("requset to set userCheck with positive vote :", userCheckDTO);

		userCheckDTO.setCategory("like");
		userCheckDTO.setVoteType("positive");
		UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
		userCheck = userCheckRepository.save(userCheck);
		userCheckDTO = userCheckMapper.toDto(userCheck);

		// anjali
		FeedDTO feedDto = new FeedDTO();

		if (userCheckDTO.getCheckedHelpId() != null) {

			feedDto.setType("HelpIsLiked");
			feedDto.setReferenceId(userCheckDTO.getCheckedHelpId());
			feedDto.setRegisteredUserId(userCheckDTO.getCheckedUserId());

			saveFeed(feedDto);
		}

		// anjali

		Optional<UserCheckDTO> result = Optional.of(userCheckDTO);
		return result;
	}

	/**
	 * set the userChecks with negative vouteType.
	 *
	 * @param pageable
	 *            the pagination information
	 * 
	 * @return the list of entities
	 * @throws IOException
	 */
	@Override
	public Optional<UserCheckDTO> saveUserCheckDislike(UserCheckDTO userCheckDTO) throws IOException {
		log.debug("requset to set userCheck with positive vote :", userCheckDTO);
		userCheckDTO.setVoteType("negative");
		UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
		userCheck = userCheckRepository.save(userCheck);
		userCheckDTO = userCheckMapper.toDto(userCheck);

		// anjali
		FeedDTO feedDto = new FeedDTO();

		if (userCheckDTO.getCheckedHelpId() != null) {

			feedDto.setType("HelpIsDisLiked");
			feedDto.setReferenceId(userCheckDTO.getCheckedHelpId());
			feedDto.setRegisteredUserId(userCheckDTO.getCheckedUserId());

			saveFeed(feedDto);
		}

		// anjali
		Optional<UserCheckDTO> result = Optional.of(userCheckDTO);
		return result;
	}

	/**
	 * Save a comment.
	 *
	 * @param commentDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws IOException
	 */
	@Override
	public CommentDTO saveComment(CommentDTO commentDTO) throws IOException {
		log.debug("Request to save Comment : {}", commentDTO);

		if (commentDTO.getDateInString() != null) {
			String parseDate = commentDTO.getDateInString().replace(" ", "T").concat("Z");

			Instant dateInstant = Instant.parse(parseDate);
			commentDTO.setDate(dateInstant);
		}

		Comment comment = commentMapper.toEntity(commentDTO);
		comment = commentRepository.save(comment);

		// anjali to post feed after comments on help, need and Post

		FeedDTO feedDto = new FeedDTO();

		if (commentDTO.getHelpId() != null) {
			feedDto.setType("HelpPostComment");
			feedDto.setReferenceId(commentDTO.getHelpId());
			feedDto.setRegisteredUserId(commentDTO.getCommentedUserId());

			saveFeed(feedDto);
		}

		if (commentDTO.getNeedId() != null) {
			feedDto.setType("NeedPostComment");
			feedDto.setReferenceId(commentDTO.getNeedId());
			feedDto.setRegisteredUserId(commentDTO.getCommentedUserId());

			saveFeed(feedDto);
		}

		if (commentDTO.getPostId() != null) {
			feedDto.setType("PostComment");
			feedDto.setReferenceId(commentDTO.getPostId());
			feedDto.setRegisteredUserId(commentDTO.getCommentedUserId());

			saveFeed(feedDto);
		}
		// anjali
		return commentMapper.toDto(comment);
	}

	/**
	 * find all comments By NeedId
	 * 
	 * @param pageable
	 *            the pagination information
	 * @Param needId
	 */
	@Override
	public Page<CommentDTO> findAllCommentsByNeedId(Pageable pageable, Long needId) {
		log.debug("request to get all comments by needId :" + needId);
		Page<CommentDTO> commentDtos = commentRepository.findAllCommentsByNeedId(pageable, needId)
				.map(commentMapper::toDto);
		List<CommentDTO> commentDtoList = calculateCommentTimePostedBefore(commentDtos);
		Page<CommentDTO> page = new PageImpl<CommentDTO>(commentDtoList, pageable, commentDtoList.size());
		return page;
	}

	/**
	 * Get all the comments by helpId.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	public Page<CommentDTO> findAllCommentsByHelpId(Pageable pageable, Long helpId) {
		log.debug("request to get all comments by helpId :" + helpId);
		Page<CommentDTO> commentDtos = commentRepository.findAllCommentsByHelpId(pageable, helpId)
				.map(commentMapper::toDto);
		List<CommentDTO> commentDtoList = calculateCommentTimePostedBefore(commentDtos);
		Page<CommentDTO> page = new PageImpl<CommentDTO>(commentDtoList, pageable, commentDtoList.size());
		return page;

	}

	/**
	 * Get all the comments with time.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	public List<CommentDTO> calculateCommentTimePostedBefore(Page<CommentDTO> comments) {
		log.debug("Request to get all comments");
		List<CommentDTO> commentList = comments.getContent();
		for (CommentDTO commentDto : commentList) {
			Instant instant = Instant.now();
			Date repliedTime = null;
			if (commentDto.getDate() != null) {
				repliedTime = Date.from(commentDto.getDate());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (repliedTime != null) {
				diffInSecond = (current.getTime() - repliedTime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				commentDto.setTimeElapsed(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				commentDto.setTimeElapsed(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				commentDto.setTimeElapsed(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				commentDto.setTimeElapsed(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				commentDto.setTimeElapsed(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				commentDto.setTimeElapsed(postedBefore + " years ago");
			}
			System.out.println("how many ago " + commentDto.getTimeElapsed());

		}
		return commentList;
	}

	/**
	 * Save a reply.
	 *
	 * @param replyDTO
	 *            the entity to save
	 * 
	 * @return the persisted entity
	 */
	@Override
	public ReplyDTO saveReply(ReplyDTO replyDTO) {
		log.debug("Request to save Reply : {}", replyDTO);
		String parseDate = replyDTO.getDateInString().replace(" ", "T").concat("Z");
		Instant date = Instant.parse(parseDate);
		replyDTO.setDate(date);
		Reply reply = replyMapper.toEntity(replyDTO);
		reply = replyRepository.save(reply);
		return replyMapper.toDto(reply);
	}

	/**
	 * find all replys By CommentId
	 * 
	 * @param pageable
	 *            the pagination information
	 * @Param CommentId
	 */
	@Override
	public Page<ReplyDTO> findAllRepliesByCommentId(Pageable pageable, Long commentId) {
		log.debug("Request to get Reply from commentId : {}", commentId);
		Page<ReplyDTO> replyDTOs = replyRepository.findAllRepliesByCommentId(pageable, commentId)
				.map(replyMapper::toDto);
		return findAllReplies(pageable, replyDTOs);
	}

	/**
	 * find all replies along with time
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the entities
	 */
	public Page<ReplyDTO> findAllReplies(Pageable pageable, Page<ReplyDTO> replyDTOs) {
		log.debug("Request to get all replies along with time");
		List<ReplyDTO> replyList = replyDTOs.getContent();
		for (ReplyDTO replyDto : replyList) {
			Instant instant = Instant.now();
			Date repliedTime = null;
			if (replyDto.getDate() != null) {
				repliedTime = Date.from(replyDto.getDate());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (repliedTime != null) {
				diffInSecond = (current.getTime() - repliedTime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				replyDto.setTimeElapsed(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				replyDto.setTimeElapsed(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				replyDto.setTimeElapsed(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				replyDto.setTimeElapsed(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				replyDto.setTimeElapsed(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				replyDto.setTimeElapsed(postedBefore + " years ago");
			}
			System.out.println("how many ago " + replyDto.getTimeElapsed());

		}

		Page<ReplyDTO> page = new PageImpl<ReplyDTO>(replyList, pageable, replyList.size());

		return page;

	}

	/**
	 * Get one severity by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<SeverityDTO> findOneseverity(Long id) {
		log.debug("Request to get Severity : {}", id);
		return severityRepository.findById(id).map(severityMapper::toDto);
	}

	/**
	 * Get all the severities.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<SeverityDTO> findAllSeverities(Pageable pageable) {
		log.debug("Request to get all Severities");
		return severityRepository.findAll(pageable).map(severityMapper::toDto);
	}

	// Code:Ruhail
	@Override
	public PostDTO savePost(PostDTO postDTO) {
		log.debug("Request to save news feed");
		// TODO Auto-generated method stub
		if (postDTO.getDateInString() != null) {
			String parseDate = postDTO.getDateInString().replace(" ", "T").concat("Z");
			Instant dateInstant = Instant.parse(parseDate);
			postDTO.setDate(dateInstant);
		} else {
			postDTO.setDate(Instant.now());
		}

		Post post = postMapper.toEntity(postDTO);
		Post savedPost = postRepository.save(post);
		PostDTO savedPostDto = postMapper.toDto(savedPost);
		log.debug("Request to see news feed saved");
		/*
		 * MultipartFile[] attachedFiles = postDTO.getAttachedFiles(); for
		 * (MultipartFile file : attachedFiles) { MediaDTO mediaDTO = new
		 * MediaDTO(); mediaDTO.setFile(file);
		 * mediaDTO.setPostId(savedPostDto.getId());
		 * mediaService.save(mediaDTO);
		 * log.debug("Request news feed media saved"); }
		 */
		return savedPostDto;
	}
	// Code:End
	// Code:Ruhail

	@Override
	public Page<PostDTO> findAllPosts(Pageable pageable) {
		log.debug("Request to get all NewsFeeds");

		Page<Post> postsPage = postRepository.findAll(pageable);
		List<Post> postsList = postsPage.getContent();
		Page<PostDTO> postsDtoPage = postsPage.map(postMapper::toDto);
		List<PostDTO> postsDtoList = postsDtoPage.getContent();

		// populating urls of post from returned domain objects to
		// corresponding dtos

		for (Post post : postsList) {
			/*
			 * Set<Media> medias = newsFeed.getAttachments(); String url;
			 * Set<String> urls = new TreeSet<String>(); for (Media m : medias)
			 * { url = m.getUrl(); urls.add(url); }
			 */
			for (PostDTO postDto : postsDtoList) {
				if (post.getId() == postDto.getId()) {
					// postDto.setAttachedFilesUrls(urls);
				}
				postDto.setTotalComments((long) calculateCountOfPostCommentsByPostId(postDto.getId()));
				postDto.setTotalLikes((long) calculateCountOfPostLikesByPostId(postDto.getId()));
				postDto.setTotalDislikes((long) calculateCountOfPostDislikesByPostId(postDto.getId()));
				RegisteredUser registeredUser = findOneRegisteredUser(postDto.getId()).get();
				postDto.setPostedUserName(registeredUser.getFirstName() + registeredUser.getLastName());

			}
		}

		// Calculating and populating the postedBefore time for each newsFeed
		// dtos

		for (PostDTO postDto : postsDtoList) {

			Date postedDate = null;
			if (postDto.getDate() != null) {
				postedDate = Date.from(postDto.getDate());
				postDto.setPostedBefore(calculateTimeDifferenceBetweenCurrentAndPostedTime(postedDate).toString());
			}

		}
		Page<PostDTO> postDtos = new PageImpl<PostDTO>(postsDtoList, pageable, postsDtoList.size());
		return postDtos;
	}

	// Code:End
	// Code:Ruhail
	/**
	 * Get count of userChecks to post by postId.
	 *
	 * @param Long
	 *            postId to find
	 * @return the count of userChecks
	 */

	@Override
	public Integer calculateCountOfPostLikesByPostId(Long postId) {
		// TODO Auto-generated method stub
		return userCheckRepository.countOfVoteTypeLike("positive", postId);
	}

	// Code:End
	// Code:Ruhail
	/**
	 * Get count of userChecks to post by postId.
	 *
	 * @param String
	 *            postId to find
	 * @return the count of userChecks
	 */

	@Override
	public Integer calculateCountOfPostDislikesByPostId(Long postId) {
		// TODO Auto-generated method stub
		return userCheckRepository.countOfVoteTypeLike("negative", postId);
	}

	// Code:End
	// Code:Ruhail
	/**
	 * Get count of userChecks to post by postId.
	 *
	 * @param String
	 *            postId to find
	 * @return the count of userChecks
	 */

	@Override
	public Integer calculateCountOfPostCommentsByPostId(Long postId) {
		// TODO Auto-generated method stub
		return commentRepository.countOfCommentsByPostId(postId);
	}

	// Code:End
	// Code:Ruhail
	/**
	 * Find time difference between current date and posted date.
	 *
	 * @param postedDate
	 *            to find the time
	 * 
	 * @return the time
	 */

	@Override
	public String calculateTimeDifferenceBetweenCurrentAndPostedTime(Date postedDate) {
		Instant instant = Instant.now();

		Date current = Date.from(instant);
		long diffInSecond = 0l;
		String diffInString = null;
		if (postedDate != null) {
			diffInSecond = (current.getTime() - postedDate.getTime()) / 1000l;
		}
		long postedBefore = 0l;
		if (diffInSecond < 60l) {
			diffInString = diffInSecond + " seconds ago";
		} else if (diffInSecond < 3600l) {
			postedBefore = diffInSecond / 60l;
			diffInString = postedBefore + " minutes ago";
		} else if (diffInSecond < 86400l) {
			postedBefore = diffInSecond / 3600l;
			diffInString = postedBefore + " hours ago";
		} else if (diffInSecond < 2592000l) {
			postedBefore = diffInSecond / 86400l;
			diffInString = postedBefore + " days ago";
		} else if (diffInSecond < 31104000l) {
			postedBefore = diffInSecond / 2592000l;
			diffInString = postedBefore + " months ago";
		} else {
			postedBefore = diffInSecond / 31104000l;
			diffInString = postedBefore + " years ago";
		}

		return diffInString;
	}
	// code:End

	// anjali

	/**
	 * Save a feed.
	 *
	 * @param feedDTO
	 *            the entity to save
	 * @return the persisted entity
	 * @throws IOException
	 */
	@Override
	public FeedDTO saveFeed(FeedDTO feedDTO) throws IOException {
		log.debug("Request to save Feed : {}", feedDTO);

		if (feedDTO.getType().equals("NeedPostAfterApproval")) {

			feedDTO.setTitle("User Posted a new Need");
		} else if (feedDTO.getType().equals("HelpPostAfterCompletion")) {

			feedDTO.setTitle("User Helped a need");
		} else if (feedDTO.getType().equals("PublishPost")) { // todo

			feedDTO.setTitle("User published a new Post");
		} else if (feedDTO.getType().equals("HelpPostComment")) {

			feedDTO.setTitle("User commented on the Help");
		} else if (feedDTO.getType().equals("HelpIsLiked")) {

			feedDTO.setTitle("User Liked on the Help");
		} else if (feedDTO.getType().equals("HelpIsDisLiked")) {

			feedDTO.setTitle("User DisLiked the Help");
		} else if (feedDTO.getType().equals("NeedComment")) {

			feedDTO.setTitle("User commented on the Need");
		} else if (feedDTO.getType().equals("NeedIsGenuine")) {

			feedDTO.setTitle("User marked the need as Genuine");
		} else if (feedDTO.getType().equals("NeedIsFake")) {

			feedDTO.setTitle("User marked the need as Fake");
		} else if (feedDTO.getType().equals("PostComment")) {

			feedDTO.setTitle("User commented on the post"); // todo
		} else if (feedDTO.getType().equals("PostLike")) { // todo

			feedDTO.setTitle("User Liked the post");
		} else if (feedDTO.getType().equals("PostDislike")) { // todo

			feedDTO.setTitle("User DisLiked the post");
		}

		Feed feed = feedMapper.toEntity(feedDTO);
		feed = feedRepository.save(feed);
		return feedMapper.toDto(feed);

	}

	// anjali

	/**
	 * Get all the feeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	public Page<FeedDTO> findAllFeeds(Pageable pageable) {
		log.debug("Request to get all Feeds");
		return feedRepository.findAll(pageable).map(feedMapper::toDto);

	}

	/**
	 * Get all the feeds by Registered User Id.
	 *
	 * @param pageable
	 *            the pagination information,registeredUserId the id of the user
	 * @return the list of entities
	 */
	@Override
	public Page<FeedDTO> findAllFeedsByRegisteredUserId(Pageable pageable, Long registeredUserId) {
		log.debug("Request to get all Feeds by Registered user id");
		return feedRepository.findAllFeedsByRegisteredUserId(pageable, registeredUserId).map(feedMapper::toDto);

	}

	// Code:Ruhail
	/**
	 * Get post by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the Optional PostDTO
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PostDTO> findOnePost(Long id) {
		log.debug("Request to get Post : {}", id);
		Post post = postRepository.findById(id).orElse(null);
		PostDTO postDto = null;
		if (post != null) {
			postDto = postMapper.toDto(post);
			postDto.setTotalComments((long) calculateCountOfPostCommentsByPostId(postDto.getId()));
			postDto.setTotalLikes((long) calculateCountOfPostLikesByPostId(postDto.getId()));
			postDto.setTotalDislikes((long) calculateCountOfPostDislikesByPostId(postDto.getId()));
			if (postDto != null) {
				RegisteredUser registeredUser = findOneRegisteredUser(postDto.getId()).get();
				postDto.setPostedUserName(registeredUser.getFirstName() + registeredUser.getLastName());
			}

			Date postedDate = null;
			if (postDto.getDate() != null) {
				postedDate = Date.from(postDto.getDate());
				postDto.setPostedBefore(calculateTimeDifferenceBetweenCurrentAndPostedTime(postedDate).toString());
			}
		}
		return Optional.of(postDto);
	}

	// Code:End
	// Code:Ruhail
	/**
	 * Get one registeredUser by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional
	public Optional<RegisteredUser> findOneRegisteredUser(Long id) {
		log.debug("Request to get RegisteredUser : {}", id);
		return registeredUserRepository.findById(id);
	}
	// Code:End



		// neeraja

	/**
	 * to add and update the social and emotional quotient
	 *
	 * @param registeredUserid
	 *            the id of the entity
	 * @return the dto
	 */

				@Override
				public RegisteredUserDTO updateRegisteredUserEmotionalQuotientSocialQuotient(Long registeredUserId) {
					
					 log.debug("REST request to Eq Sq LoggedUser : {}", registeredUserId);
				       RegisteredUserDTO registeredUserDto=new RegisteredUserDTO();
				       RegisteredUser registeredUser1=new RegisteredUser();
				    		   
				      RegisteredUser registeredUser = registeredUserRepository.findById(registeredUserId).orElse(null);
				      if(registeredUser.getSocialQuotient() == null)
				      {
				    	  registeredUser.setSocialQuotient(0l);
				    	  registeredUser.setEmotionalQuotient(0l);
				      }  
				      Long pointOfHelpEq = helpRepository.findCountOfHelpsByRegisteredUserId(registeredUserId)/3;
				      Long pointOfHelpSq = helpRepository.findCountOfHelpsByRegisteredUserId(registeredUserId);
				      Long pointOfPostEq = postRepository.findCountOfPostsByRegisteredUserId(registeredUserId)/6;
				      Long pointOfPostSq = postRepository.findCountOfPostsByRegisteredUserId(registeredUserId)/12;
				      registeredUser.setEmotionalQuotient(pointOfHelpEq+pointOfPostEq);
				      registeredUser.setSocialQuotient(pointOfHelpSq+pointOfPostSq);
				      registeredUserDto.setFirstName(registeredUser.getFirstName());
				      registeredUserDto.setLastName(registeredUser.getLastName());
				      registeredUserDto.setBloodGroup(registeredUser.getBloodGroup());
				      registeredUserDto.setEmail(registeredUser.getEmail());
				      registeredUserDto.setDescription(registeredUser.getDescription());
				      registeredUserDto.setDob(registeredUser.getDob());
				      registeredUserDto.setGender(registeredUser.getGender());
				      registeredUserDto.setHappinessIndex(registeredUser.getHappinessIndex());
				      registeredUserDto.setProfession(registeredUser.getProfession());
				      registeredUserDto.setEmotionalQuotient(registeredUser.getEmotionalQuotient());
				      registeredUserDto.setSocialQuotient(registeredUser.getSocialQuotient());
				      registeredUserDto.setProfilePicId(registeredUser.getProfilePic().getId());
				      registeredUserDto.setRating(registeredUser.getRating());
				      
				     
				      
				      registeredUser1= registeredUserRepository.save(registeredUser);
				     // return registeredUserMapper.toDto(registeredUser);
				      return registeredUserDto;
				    }
				
		// end neeraja

}
