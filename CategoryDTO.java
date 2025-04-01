import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class categoryDTO {
    private int id;
    private String nome;


    public CategoryDTO(Long id, String name) {
        this.id = id
        this.name = name;
    }

    public CategoryDTIO(Category entity) {
        this.id = intity.getId();
        this.name = entity.getName();
    }
    //get e setter

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
