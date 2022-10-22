package org.david.assassinos.db;

import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import org.david.assassinos.App;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Assassino extends Entity<Assassino> implements Cloneable {
    private String nome;
    private String sobrenome;
    private LocalDateTime dataNascimento;
    private String armaFavorita;
    private String cidadeAtual;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getArmaFavorita() {
        return armaFavorita;
    }

    public void setArmaFavorita(String armaFavorita) {
        this.armaFavorita = armaFavorita;
    }

    public String getCidadeAtual() {
        return cidadeAtual;
    }

    public void setCidadeAtual(String cidadeAtual) {
        this.cidadeAtual = cidadeAtual;
    }

    public Assassino() {
        super(App.db.assassinos);

        dataNascimento = LocalDateTime.now();
    }

    public Assassino(String nome, String sobrenome, LocalDateTime dataNascimento, String armaFavorita, String cidadeAtual) {
        super(App.db.assassinos);

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.armaFavorita = armaFavorita;
        this.cidadeAtual = cidadeAtual;
    }

    public Assassino(Assassino assassino) {
        super(App.db.assassinos);

        this.nome = assassino.nome;
        this.sobrenome = assassino.sobrenome;
        this.dataNascimento = assassino.dataNascimento;
        this.armaFavorita = assassino.armaFavorita;
        this.cidadeAtual = assassino.cidadeAtual;
    }

    @Override
    public void fromRow(Object[] o) throws DateTimeParseException {
        String[] row = Arrays.stream(o).toArray(String[]::new);

        id = new ObjectId(row[0]);
        nome = row[1];
        sobrenome = row[2];
        dataNascimento = LocalDateTime.parse(row[3]);
        armaFavorita = row[4];
        cidadeAtual = row[5];
    }

    @Override
    public Object[] toRow() {
        return new Object[] {
            id.toString(),
            nome,
            sobrenome,
            dataNascimento.toString(),
            armaFavorita,
            cidadeAtual
        };
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null) return false;

        if(obj.getClass() != getClass()) return false;

        Assassino a = (Assassino) obj;

        if(!nome.equals(a.nome)) return false;
        if(!sobrenome.equals(a.sobrenome)) return false;
        if(!dataNascimento.equals(a.dataNascimento)) return false;
        if(!armaFavorita.equals(a.armaFavorita)) return false;
        if(!cidadeAtual.equals(a.cidadeAtual)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;

        ret = 7 * ret + nome.hashCode();
        ret = 7 * ret + sobrenome.hashCode();
        ret = 7 * ret + dataNascimento.hashCode();
        ret = 7 * ret + armaFavorita.hashCode();
        ret = 7 * ret + cidadeAtual.hashCode();

        if(ret < 0) ret = -ret;

        return ret;
    }

    @Override
    public String toString() {
        return "Assassino{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", armaFavorita='" + armaFavorita + '\'' +
                ", cidadeAtual='" + cidadeAtual + '\'' +
                '}';
    }

    @Override
    public Assassino clone() {
        return new Assassino(this);
    }
}
