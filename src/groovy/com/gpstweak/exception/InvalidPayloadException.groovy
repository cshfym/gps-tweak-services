package com.gpstweak.exception

class InvalidPayloadException {

  String message

  public InvalidPayloadException(String message) {
    this.message = message
  }

  def getMessage() {
    message
  }
}
