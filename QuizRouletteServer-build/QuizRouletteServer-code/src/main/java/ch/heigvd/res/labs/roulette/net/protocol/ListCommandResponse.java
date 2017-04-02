package ch.heigvd.res.labs.roulette.net.protocol;

import ch.heigvd.res.labs.roulette.data.Student;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to serialize/deserialize the response sent by the server
 * when processing the "LIST" command defined in the protocol specification. The
 * JsonObjectMapper utility class can use this class.
 *
 * @author Dbnsky & Iamfonky
 */

public class ListCommandResponse {
    private Student[] listStudent;

    public ListCommandResponse(){

    }

    public ListCommandResponse(List<Student> list) {

        this.listStudent = (Student[]) list.toArray();
    }

    public List<Student> getList() {
        return Arrays.asList(listStudent);
    }

    public Student getAtList(int index){
        return listStudent[index];
    }

}
