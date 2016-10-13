package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class DivisionResponse {

  @JsonProperty
  @NotNull
  private Integer quotient;

  public DivisionResponse(int quotient) {
    this.quotient = quotient;
  }

  @SuppressWarnings("unused") // for Jackson
  private DivisionResponse() {}

  public int getQuotient() {
    return quotient;
  }
}
