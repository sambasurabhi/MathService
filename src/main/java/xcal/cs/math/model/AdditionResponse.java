package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AdditionResponse {

  @JsonProperty
  @NotNull
  private Long sum;

  public AdditionResponse(long sum) {
    this.sum = sum;
  }

  @SuppressWarnings("unused") // for Jackson
  private AdditionResponse() {}

  public long getSum() {
    return sum;
  }
}
