package com.dbg.travisapp.dummy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Dummy {

  protected static final ObjectMapper MAPPER = new ObjectMapper();

  public static <T> String toJSON(T t) throws JsonProcessingException {
    return MAPPER.writeValueAsString(t);
  }
}
