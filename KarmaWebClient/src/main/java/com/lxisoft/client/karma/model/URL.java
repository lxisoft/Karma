package com.lxisoft.client.karma.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.lxisoft.client.karma.model.URLStreamHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * URL
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2018-11-28T11:53:04.824+05:30[Asia/Calcutta]")

public class URL   {
  @JsonProperty("authority")
  private String authority = null;

  @JsonProperty("content")
  private Object content = null;

  @JsonProperty("defaultPort")
  private Integer defaultPort = null;

  @JsonProperty("deserializedFields")
  private URLStreamHandler deserializedFields = null;

  @JsonProperty("file")
  private String file = null;

  @JsonProperty("host")
  private String host = null;

  @JsonProperty("path")
  private String path = null;

  @JsonProperty("port")
  private Integer port = null;

  @JsonProperty("protocol")
  private String protocol = null;

  @JsonProperty("query")
  private String query = null;

  @JsonProperty("ref")
  private String ref = null;

  @JsonProperty("serializedHashCode")
  private Integer serializedHashCode = null;

  @JsonProperty("userInfo")
  private String userInfo = null;

  public URL authority(String authority) {
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

  public URL content(Object content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  **/
  @ApiModelProperty(value = "")


  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }

  public URL defaultPort(Integer defaultPort) {
    this.defaultPort = defaultPort;
    return this;
  }

  /**
   * Get defaultPort
   * @return defaultPort
  **/
  @ApiModelProperty(value = "")


  public Integer getDefaultPort() {
    return defaultPort;
  }

  public void setDefaultPort(Integer defaultPort) {
    this.defaultPort = defaultPort;
  }

  public URL deserializedFields(URLStreamHandler deserializedFields) {
    this.deserializedFields = deserializedFields;
    return this;
  }

  /**
   * Get deserializedFields
   * @return deserializedFields
  **/
  @ApiModelProperty(value = "")

  @Valid

  public URLStreamHandler getDeserializedFields() {
    return deserializedFields;
  }

  public void setDeserializedFields(URLStreamHandler deserializedFields) {
    this.deserializedFields = deserializedFields;
  }

  public URL file(String file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
  **/
  @ApiModelProperty(value = "")


  public String getFile() {
    return file;
  }

  public void setFile(String file) {
    this.file = file;
  }

  public URL host(String host) {
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

  public URL path(String path) {
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

  public URL port(Integer port) {
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

  public URL protocol(String protocol) {
    this.protocol = protocol;
    return this;
  }

  /**
   * Get protocol
   * @return protocol
  **/
  @ApiModelProperty(value = "")


  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public URL query(String query) {
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

  public URL ref(String ref) {
    this.ref = ref;
    return this;
  }

  /**
   * Get ref
   * @return ref
  **/
  @ApiModelProperty(value = "")


  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public URL serializedHashCode(Integer serializedHashCode) {
    this.serializedHashCode = serializedHashCode;
    return this;
  }

  /**
   * Get serializedHashCode
   * @return serializedHashCode
  **/
  @ApiModelProperty(value = "")


  public Integer getSerializedHashCode() {
    return serializedHashCode;
  }

  public void setSerializedHashCode(Integer serializedHashCode) {
    this.serializedHashCode = serializedHashCode;
  }

  public URL userInfo(String userInfo) {
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
    URL URL = (URL) o;
    return Objects.equals(this.authority, URL.authority) &&
        Objects.equals(this.content, URL.content) &&
        Objects.equals(this.defaultPort, URL.defaultPort) &&
        Objects.equals(this.deserializedFields, URL.deserializedFields) &&
        Objects.equals(this.file, URL.file) &&
        Objects.equals(this.host, URL.host) &&
        Objects.equals(this.path, URL.path) &&
        Objects.equals(this.port, URL.port) &&
        Objects.equals(this.protocol, URL.protocol) &&
        Objects.equals(this.query, URL.query) &&
        Objects.equals(this.ref, URL.ref) &&
        Objects.equals(this.serializedHashCode, URL.serializedHashCode) &&
        Objects.equals(this.userInfo, URL.userInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authority, content, defaultPort, deserializedFields, file, host, path, port, protocol, query, ref, serializedHashCode, userInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URL {\n");
    
    sb.append("    authority: ").append(toIndentedString(authority)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    defaultPort: ").append(toIndentedString(defaultPort)).append("\n");
    sb.append("    deserializedFields: ").append(toIndentedString(deserializedFields)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
    sb.append("    ref: ").append(toIndentedString(ref)).append("\n");
    sb.append("    serializedHashCode: ").append(toIndentedString(serializedHashCode)).append("\n");
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

