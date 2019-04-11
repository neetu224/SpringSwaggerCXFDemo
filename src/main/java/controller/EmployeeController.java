package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Employee;

@Path("/")
@Api(value = "Employee resource Version 1", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {
	private static final Log logger = LogFactory.getLog(EmployeeController.class);

	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee resource found", response = Employee.class),
			@ApiResponse(code = 404, message = "Employee resource not found") })
	@ApiOperation(value = "Gets a Employee resource. Version 1 - (version in URL)")
	@GET
	@Path("/rest/emp/dummy")
	public Employee getDummyEmployee() {
		logger.info("Start getDummyEmployee");
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		empData.put(9999, emp);
		// return Response.status(Status.OK).entity(emp).build();
		return emp;
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Employee resource found", response = Employee.class),
			@ApiResponse(code = 404, message = "Employee resource not found") })
	@ApiOperation(value = "Gets a Employee resource. Version 1 - (version in Accept Header)")
	@GET
	@Path("/rest/emp/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getEmployee(@PathParam("id") int empId) {
		logger.info("Start getEmployee. ID=" + empId);
		return Response.status(Status.OK).entity(empData.get(empId)).build();
	}
}
