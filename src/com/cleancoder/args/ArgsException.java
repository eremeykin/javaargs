package com.cleancoder.args;

public class ArgsException extends Exception {
  private final ErrorCode errorCode;
  private final String errorParameter;
  private final char errorArgumentId;

  public ArgsException(ErrorCode errorCode) {
    this(errorCode, '\0', null);
  }

  public ArgsException(ErrorCode errorCode, String errorParameter) {
    this(errorCode, '\0', errorParameter);
  }

  public ArgsException(ErrorCode errorCode, char errorArgumentId, String errorParameter) {
    this.errorCode = errorCode;
    this.errorParameter = errorParameter;
    this.errorArgumentId = errorArgumentId;
  }

  public char getErrorArgumentId() {
    return errorArgumentId;
  }

  public String getErrorParameter() {
    return errorParameter;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public ArgsException withErrorArgumentId(char argChar) {
    return new ArgsException(this.errorCode, argChar, this.errorParameter);
  }

  public String errorMessage() {
    switch (errorCode) {
      case UNEXPECTED_ARGUMENT:
        return String.format("Argument -%c unexpected.", errorArgumentId);
      case MISSING_STRING:
        return String.format("Could not find string parameter for -%c.", errorArgumentId);
      case INVALID_INTEGER:
        return String.format("Argument -%c expects an integer but was '%s'.", errorArgumentId, errorParameter);
      case MISSING_INTEGER:
        return String.format("Could not find integer parameter for -%c.", errorArgumentId);
      case INVALID_DOUBLE:
        return String.format("Argument -%c expects a double but was '%s'.", errorArgumentId, errorParameter);
      case MISSING_DOUBLE:
        return String.format("Could not find double parameter for -%c.", errorArgumentId);
      case INVALID_ARGUMENT_NAME:
        return String.format("'%c' is not a valid argument name.", errorArgumentId);
      case INVALID_ARGUMENT_FORMAT:
        return String.format("'%s' is not a valid argument format.", errorParameter);
      case MISSING_MAP:
        return String.format("Could not find map string for -%c.", errorArgumentId);
      case MALFORMED_MAP:
        return String.format("Map string for -%c is not of form k1:v1,k2:v2...", errorArgumentId);
    }
    return "";
  }

  public enum ErrorCode {
    INVALID_ARGUMENT_FORMAT,
    UNEXPECTED_ARGUMENT,
    INVALID_ARGUMENT_NAME,
    MISSING_STRING,
    MISSING_INTEGER,
    INVALID_INTEGER,
    MISSING_DOUBLE,
    MALFORMED_MAP,
    MISSING_MAP,
    INVALID_DOUBLE
  }
}
