package org.david.assassinos.db;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class Assassino implements Cloneable {
    private ObjectId id;
    private String nome;
    private String sobrenome;
    private LocalDateTime dataNascimento;
    private String armaFavorita;
    private String cidadeAtual;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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
        id = new ObjectId();
    }

    public Assassino(String nome, String sobrenome, LocalDateTime dataNascimento, String armaFavorita, String cidadeAtual) {
        id = new ObjectId();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.armaFavorita = armaFavorita;
        this.cidadeAtual = cidadeAtual;
    }

    public Assassino(Assassino assassino) {
        id = new ObjectId();
        this.nome = assassino.nome;
        this.sobrenome = assassino.sobrenome;
        this.dataNascimento = assassino.dataNascimento;
        this.armaFavorita = assassino.armaFavorita;
        this.cidadeAtual = assassino.cidadeAtual;
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

    public Assassino clone() {
        return new Assassino(this);
    }
}
