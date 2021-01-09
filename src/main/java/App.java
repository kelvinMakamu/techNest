import departments.Department;
import departments.DepartmentService;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getAssignedServerPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        return processBuilder.environment().get("PORT") != null
                ? Integer.parseInt(processBuilder.environment().get("PORT"))
                : 4500;
    }

    public static void main(String[] args) {

        port(getAssignedServerPort());
        staticFileLocation("/public");

        String DB_URL = "jdbc:postgresql://localhost:5432/technest_test";
        Sql2o sql2o   = new Sql2o(DB_URL, null, null);
        DepartmentService departmentService = new DepartmentService(sql2o);

        get("/",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/departments",(req,res)->{
            Map<String,Object> model     = new HashMap<>();
            List<Department> departments = departmentService.getAllDepartments();
            model.put("departments",departments);
            return new ModelAndView(model,"department.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
