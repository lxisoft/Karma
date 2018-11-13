package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * URI
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-11T21:56:15.510+05:30[Asia/Calcutta]")

public class URI   {
  @JsonProperty("absolute")
  private Boolean absolute = null;

  @JsonProperty("authority")
  private String authority = null;

  @JsonProperty("fragment")
  private String fragment = null;

  @JsonProperty("host")
  private String host = null;

  @JsonProperty("opaque")
  private Boolean opaque = null;

  @JsonProperty("path")
  private String path = null;

  @JsonProperty("port")
  private Integer port = null;

  @JsonProperty("query")
  private String query = null;

  @JsonProperty("rawAuthority")
  private String rawAuthority = null;

  @JsonProperty("rawFragment")
  private String rawFragment = null;

  @JsonProperty("rawPath")
  private String rawPath = null;

  @JsonProperty("rawQuery")
  private String rawQuery = null;

  @JsonProperty("rawSchemeSpecificPart")
  private String rawSchemeSpecificPart = null;

  @JsonProperty("rawUserInfo")
  private String rawUserInfo = null;

  @JsonProperty("scheme")
  private String scheme = null;

  @JsonProperty("schemeSpecificPart")
  private String schemeSpecificPart = null;

  @JsonProperty("userInfo")
  private String userInfo = null;

  public URI absolute(Boolean absolute) {
    this.absolute = absolute;
    return this;
  }

  /**
   * Get absolute
   * @return absolute
  **/
  @ApiModelProperty(value = "")


  public Boolean isAbsolute() {
    return absolute;
  }

  public void setAbsolute(Boolean absolute) {
    this.absolute = absolute;
  }

  public URI authority(String authority) {
    this.authority = authority;
    return this;
  }

  /**
   * Get authority
   * @return authority
  **/
  @ApiModelProperty(value = "")


  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public URI fragment(String fragment) {
    this.fragment = fragment;
    return this;
  }

  /**
   * Get fragment
   * @return fragment
  **/
  @ApiModelProperty(value = "")


  public String getFragment() {
    return fragment;
  }

  public void setFragment(String fragment) {
    this.fragment = fragment;
  }

  public URI host(String host) {
    this.host = host;
    return this;
  }

  /**
   * Get host
   * @return host
  **/
  @ApiModelProperty(value = "")


  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public URI opaque(Boolean opaque) {
    this.opaque = opaque;
    return this;
  }

  /**
   * Get opaque
   * @return opaque
  **/
  @ApiModelProperty(value = "")


  public Boolean isOpaque() {
    return opaque;
  }

  public void setOpaque(Boolean opaque) {
    this.opaque = opaque;
  }

  public URI path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
  **/
  @ApiModelProperty(value = "")


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public URI port(Integer port) {
    this.port = port;
    return this;
  }

  /**
   * Get port
   * @return port
  **/
  @ApiModelProperty(value = "")


  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public URI query(String query) {
    this.query = query;
    return this;
  }

  /**
   * Get query
   * @return query
  **/
  @ApiModelProperty(value = "")


  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public URI rawAuthority(String rawAuthority) {
    this.rawAuthority = rawAuthority;
    return this;
  }

  /**
   * Get rawAuthority
   * @return rawAuthority
  **/
  @ApiModelProperty(value = "")


  public String getRawAuthority() {
    return rawAuthority;
  }

  public void setRawAuthority(String rawAuthority) {
    this.rawAuthority = rawAuthority;
  }

  public URI rawFragment(String rawFragment) {
    this.rawFragment = rawFragment;
    return this;
  }

  /**
   * Get rawFragment
   * @return rawFragment
  **/
  @ApiModelProperty(value = "")


  public String getRawFragment() {
    return rawFragment;
  }

  public void setRawFragment(String rawFragment) {
    this.rawFragment = rawFragment;
  }

  public URI rawPath(String rawPath) {
    this.rawPath = rawPath;
    return this;
  }

  /**
   * Get rawPath
   * @return rawPath
  **/
  @ApiModelProperty(value = "")


  public String getRawPath() {
    return rawPath;
  }

  public void setRawPath(String rawPath) {
    this.rawPath = rawPath;
  }

  public URI rawQuery(String rawQuery) {
    this.rawQuery = rawQuery;
    return this;
  }

  /**
   * Get rawQuery
   * @return rawQuery
  **/
  @ApiModelProperty(value = "")


  public String getRawQuery() {
    return rawQuery;
  }

  public void setRawQuery(String rawQuery) {
    this.rawQuery = rawQuery;
  }

  public URI rawSchemeSpecificPart(String rawSchemeSpecificPart) {
    this.rawSchemeSpecificPart = rawSchemeSpecificPart;
    return this;
  }

  /**
   * Get rawSchemeSpecificPart
   * @return rawSchemeSpecificPart
  **/
  @ApiModelProperty(value = "")


  public String getRawSchemeSpecificPart() {
    return rawSchemeSpecificPart;
  }

  public void setRawSchemeSpecificPart(String rawSchemeSpecificPart) {
    this.rawSchemeSpecificPart = rawSchemeSpecificPart;
  }

  public URI rawUserInfo(String rawUserInfo) {
    this.rawUserInfo = rawUserInfo;
    return this;
  }

  /**
   * Get rawUserInfo
   * @return rawUserInfo
  **/
  @ApiModelProperty(value = "")


  public String getRawUserInfo() {
    return rawUserInfo;
  }

  public void setRawUserInfo(String rawUserInfo) {
    this.rawUserInfo = rawUserInfo;
  }

  public URI scheme(String scheme) {
    this.scheme = scheme;
    return this;
  }

  /**
   * Get scheme
   * @return scheme
  **/
  @ApiModelProperty(value = "")


  public String getScheme() {
    return scheme;
  }

  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  public URI schemeSpecificPart(String schemeSpecificPart) {
    this.schemeSpecificPart = schemeSpecificPart;
    return this;
  }

  /**
   * Get schemeSpecificPart
   * @return schemeSpecificPart
  **/
  @ApiModelProperty(value = "")


  public String getSchemeSpecificPart() {
    return schemeSpecificPart;
  }

  public void setSchemeSpecificPart(String schemeSpecificPart) {
    this.schemeSpecificPart = schemeSpecificPart;
  }

  public URI userInfo(String userInfo) {
    this.userInfo = userInfo;
    return this;
  }

  /**
   * Get userInfo
   * @return userInfo
  **/
  @ApiModelProperty(value = "")


  public String getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(String userInfo) {
    this.userInfo = userInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    URI URI = (URI) o;
    return Objects.equals(this.absolute, URI.absolute) &&
        Objects.equals(this.authority, URI.authority) &&
        Objects.equals(this.fragment, URI.fragment) &&
        Objects.equals(this.host, URI.host) &&
        Objects.equals(this.opaque, URI.opaque) &&
        Objects.equals(this.path, URI.path) &&
        Objects.equals(this.port, URI.port) &&
        Objects.equals(this.query, URI.query) &&
        Objects.equals(this.rawAuthority, URI.rawAuthority) &&
        Objects.equals(this.rawFragment, URI.rawFragment) &&
        Objects.equals(this.rawPath, URI.rawPath) &&
        Objects.equals(this.rawQuery, URI.rawQuery) &&
        Objects.equals(this.rawSchemeSpecificPart, URI.rawSchemeSpecificPart) &&
        Objects.equals(this.rawUserInfo, URI.rawUserInfo) &&
        Objects.equals(this.scheme, URI.scheme) &&
        Objects.equals(this.schemeSpecificPart, URI.schemeSpecificPart) &&
        Objects.equals(this.userInfo, URI.userInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(absolute, authority, fragment, host, opaque, path, port, query, rawAuthority, rawFragment, rawPath, rawQuery, rawSchemeSpecificPart, rawUserInfo, scheme, schemeSpecificPart, userInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URI {\n");
    
    sb.append("    absolute: ").append(toIndentedString(absolute)).append("\n");
    sb.append("    authority: ").append(toIndentedString(authority)).append("\n");
    sb.append("    fragment: ").append(toIndentedString(fragment)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    opaque: ").append(toIndentedString(opaque)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    rawAuthority: ").append(toIndentedString(rawAuthority)).append("\n");
    sb.append("    rawFragment: ").append(toIndentedString(rawFragment)).append("\n");
    sb.append("    rawPath: ").append(toIndentedString(rawPath)).append("\n");
    sb.append("    rawQuery: ").append(toIndentedString(rawQuery)).append("\n");
    sb.append("    rawSchemeSpecificPart: ").append(toIndentedString(rawSchemeSpecificPart)).append("\n");
    sb.append("    rawUserInfo: ").append(toIndentedString(rawUserInfo)).append("\n");
    sb.append("    scheme: ").append(toIndentedString(scheme)).append("\n");
    sb.append("    schemeSpecificPart: ").append(toIndentedString(schemeSpecificPart)).append("\n");
    sb.append("    userInfo: ").append(toIndentedString(userInfo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
