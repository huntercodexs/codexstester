package codexstester.bdd.stepsdef.samples;

import com.huntercodexs.codexstester.resource.parser.CodexsObjectParser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

import java.util.List;
import java.util.Map;

public class LambdaSteps implements En {

    CodexsObjectParser codexsObjectParser;

    public LambdaSteps() {

        this.codexsObjectParser = new CodexsObjectParser();

        Given("list test", (DataTable table) -> {
            System.out.println("=========+++> LIST");
            for (String string : table.asList()) {
                System.out.println(string);
            }
        });

        And("list list test", (DataTable table) -> {
            System.out.println("=========+++> LIST LIST");
            for (List<String> list : table.asLists()) {
                System.out.println(list);
            }
        });

        And("list map test", (DataTable table) -> {
            System.out.println("=========+++> LIST MAP");
            for (Map<String, String> map : table.asMaps()) {
                System.out.println(map);
            }
        });

        And("map test", (DataTable table) -> {
            System.out.println("=========+++> MAP");
            table.asMap(String.class, String.class).forEach((k, v)->{
                System.out.println("{"+k+"="+v+"}");
            });
        });

        And("map list test", (DataTable table) -> {
            System.out.println("=========+++> MAP LIST");
            Map<String, List<String>> mapList = this.codexsObjectParser.mapInList(table);

            mapList.forEach((k, v) -> {
                System.out.println(k+" => "+v);
            });
        });

        And("map map test", (DataTable table) -> {
            System.out.println("=========+++> MAP MAP");
            Map<String, Map<String, String>> mapMap = this.codexsObjectParser.mapInMap(table);

            mapMap.forEach((k, v) -> {
                System.out.println(k+" => "+v);
            });
        });

        And("with the following template string definition", (String test) -> {
            System.out.println("====> " + test);
        });
    }
}
