package org.david.assassinos.db;

import org.bson.types.ObjectId;
import org.david.assassinos.App;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

public class Vitima extends Entity<Vitima> implements Cloneable {
    private String assassinoId;

    private String nome;

    private String sobrenome;

    private LocalDateTime dataMorte;

    private String armaUtilizada;

    private String localMorte;

    public String getAssassinoId() {
        return assassinoId;
    }

    public void setAssassinoId(String assassinoId) {
        this.assassinoId = assassinoId;
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

    public LocalDateTime getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(LocalDateTime dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getArmaUtilizada() {
        return armaUtilizada;
    }

    public void setArmaUtilizada(String armaUtilizada) {
        this.armaUtilizada = armaUtilizada;
    }

    public String getLocalMorte() {
        return localMorte;
    }

    public void setLocalMorte(String localMorte) {
        this.localMorte = localMorte;
    }

    public Vitima() {
        super(App.db.vitimas);

        dataMorte = LocalDateTime.now();
    }

    public Vitima(String assassinoId, String nome, String sobrenome, LocalDateTime dataMorte, String armaUtilizada, String localMorte) {
        super(App.db.vitimas);

        this.assassinoId = assassinoId;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataMorte = dataMorte;
        this.armaUtilizada = armaUtilizada;
        this.localMorte = localMorte;
    }

    public Vitima(Vitima vitima) {
        super(App.db.vitimas);

        assassinoId = vitima.assassinoId;
        nome = vitima.nome;
        sobrenome = vitima.sobrenome;
        dataMorte = vitima.dataMorte;
        armaUtilizada = vitima.armaUtilizada;
        localMorte = vitima.localMorte;
    }

    @Override
    public void fromRow(Object[] o) throws DateTimeParseException {
        String[] row = Arrays.stream(o).toArray(String[]::new);

        id = new ObjectId(row[0]);
        assassinoId = row[1];
        nome = row[2];
        sobrenome = row[3];
        dataMorte = LocalDateTime.parse(row[4]);
        armaUtilizada = row[5];
        localMorte = row[6];
    }

    @Override
    public Object[] toRow() {
        return new Object[] {
                id.toString(),
                assassinoId,
                nome,
                sobrenome,
                dataMorte.toString(),
                armaUtilizada,
                localMorte
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if(o.getClass() != getClass()) return false;

        Vitima v = (Vitima) o;

        if(!assassinoId.equals(v.assassinoId)) return false;
        if(!nome.equals(v.nome)) return false;
        if(!sobrenome.equals(v.nome)) return false;
        if(!dataMorte.equals(v.dataMorte)) return false;
        if(!armaUtilizada.equals(v.armaUtilizada)) return false;
        if(!localMorte.equals(v.localMorte)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int ret = 1;

        ret = 7 * ret + assassinoId.hashCode();
        ret = 7 * ret + nome.hashCode();
        ret = 7 * ret + sobrenome.hashCode();
        ret = 7 * ret + dataMorte.hashCode();
        ret = 7 * ret + armaUtilizada.hashCode();
        ret = 7 * ret + localMorte.hashCode();

        if(ret < 0) ret = -ret;

        return ret;
    }

    @Override
    public String toString() {
        return "Vitima{" +
                "id=" + id +
                ", assassinoId='" + assassinoId + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", dataMorte=" + dataMorte +
                ", armaUtilizada='" + armaUtilizada + '\'' +
                ", localMorte='" + localMorte + '\'' +
                '}';
    }

    @Override
    public Vitima clone() {
        return new Vitima(this);
    }
}
