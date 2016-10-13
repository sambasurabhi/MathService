package xcal.cs.math;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import xcal.cs.math.model.AdditionRequest;
import xcal.cs.math.model.AdditionResponse;
import xcal.cs.math.model.DivisionRequest;
import xcal.cs.math.model.DivisionResponse;
import xcal.cs.math.model.IntersectRequest;
import xcal.cs.math.model.IntersectResponse;
import xcal.cs.math.model.MaxRequest;
import xcal.cs.math.model.MaxResponse;
import xcal.cs.math.model.MinRequest;
import xcal.cs.math.model.MinResponse;
import xcal.cs.math.model.MultiplicationRequest;
import xcal.cs.math.model.MultiplicationResponse;
import xcal.cs.math.model.UnionRequest;
import xcal.cs.math.model.UnionResponse;

@RestController
public class MathController {

  private static final Logger LOG = LoggerFactory.getLogger(MathController.class);

  @RequestMapping(path = "/add", method = RequestMethod.POST)
  public AdditionResponse add(@RequestBody @Valid AdditionRequest request) {
    long sum = ArithmeticUtils.addAndCheck((long)request.getAugend(), (long)request.getAddend());
    LOG.info("{} + {} = {}", request.getAugend(), request.getAddend(), sum);
    return new AdditionResponse(sum);
  }

  @RequestMapping(path = "/divide", method = RequestMethod.POST)
  public DivisionResponse divide(@RequestBody @Valid DivisionRequest request) {
	  int quotient = 0;
	  if(request.getDivisor() == 0){
		  quotient = request.getDividend() / request.getDivisor();
	  }
     
    LOG.info("{} / {} = {}", request.getDividend(), request.getDivisor(), quotient);
    return new DivisionResponse(quotient);
  }

  @RequestMapping(path = "/intersect", method = RequestMethod.POST)
  public IntersectResponse intersect(@RequestBody @Valid IntersectRequest request) {
    List<Integer> intersection = new ArrayList(request.getLeft());
    for (Integer i : request.getLeft()) {
      if (!request.getRight().contains(i)) {
        intersection.remove(i);
      }
    }
    LOG.info("INTERSECT({}, {}) = {}", request.getLeft(), request.getRight(), intersection);
    return new IntersectResponse(intersection);
  }

  @RequestMapping(path = "/max", method = RequestMethod.POST)
  public MaxResponse max(@RequestBody @Valid MaxRequest request) {
    Integer max = null;
    for (Integer i : request.getNumbers()) {
      if (max == null || i > max) {
        max = i;
      }
    }
    LOG.info("MAX({}) = {}", request.getNumbers(), max);
    return new MaxResponse(max);
  }

  @RequestMapping(path = "/min", method = RequestMethod.POST)
  public MinResponse min(@RequestBody @Valid MinRequest request) {
    Integer min = null;
    for (Integer i : request.getNumbers()) {
      if (min == null || i < min) {
        min = i;
      }
    }
    LOG.info("MIN({}) = {}", request.getNumbers(), min);
    return new MinResponse(min);
  }

  @RequestMapping(path = "/multiply", method = RequestMethod.POST)
  public MultiplicationResponse multiply(@RequestBody @Valid MultiplicationRequest request) {
    int product = request.getMultiplicand() * request.getMultiplier();
    LOG.info("{} * {} = {}", request.getMultiplicand(), request.getMultiplier(), product);
    return new MultiplicationResponse(product);
  }

  @RequestMapping(path = "/union", method = RequestMethod.POST)
  public UnionResponse union(@RequestBody @Valid UnionRequest request) {
    HashSet<Integer> union = new HashSet(request.getLeft());
    union.addAll(request.getRight());
    LOG.info("UNION(({}, {}) = {}", request.getLeft(), request.getRight(), union);
    return new UnionResponse(union);
  }
}
