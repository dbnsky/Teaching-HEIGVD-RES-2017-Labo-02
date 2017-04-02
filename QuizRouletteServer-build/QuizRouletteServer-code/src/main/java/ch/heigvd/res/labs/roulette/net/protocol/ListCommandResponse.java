package ch.heigvd.res.labs.roulette.net.protocol;

import ch.heigvd.res.labs.roulette.data.Student;
import java.util.List;

/**
 * This class is used to serialize/deserialize the response sent by the server
 * when processing the "LIST" command defined in the protocol specification. The
 * JsonObjectMapper utility class can use this class.
 *
 * @author Dbnsky & Iamfonky
 */

public class ListCommandResponse {
    private List<Student> list;

    public ListCommandResponse(){

    }

    public ListCommandResponse(List<Student> list) {
        this.list = list;
    }

    public List<Student> getList() {
        return list;
    }

    public Student getAtList(int index){
        return list.get(index);
    }
}
