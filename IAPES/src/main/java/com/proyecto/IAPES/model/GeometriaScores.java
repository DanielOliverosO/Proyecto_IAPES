package com.proyecto.IAPES.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "user_geometria_scores")
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class GeometriaScores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Id_User")
    private Long idUser;

    @Column(name = "AyFp_Percentage")
    private int ayFpPercentage;

    @Column(name = "Tp_Percentage")
    private int tpPercentage;

    @Column(name = "St_Percentage")
    private int stPercentage;

    @Column(name = "Gab_Percentage")
    private int gabPercentage;

    @Column(name = "T_Percentage")
    private int tPercentage;

    @Column(name = "VyAs_Percentage")
    private int vyAsPercentage;

    // Relación opcional con CreacionUsuarios
    @ManyToOne
    @JoinColumn(name = "Id_User", referencedColumnName = "Id_User", insertable = false, updatable = false)
    private CreacionUsuarios user;


    public GeometriaScores(Long id, Long idUser, int ayFp, int tp, int st, int gab, int t, int vyAs) {
        this.id = id;
        this.idUser = idUser;
        this.ayFpPercentage = ayFp;
        this.tpPercentage = tp;
        this.stPercentage = st;
        this.gabPercentage = gab;
        this.tPercentage = t;
        this.vyAsPercentage = vyAs;
    }

    public GeometriaScores() {

    }
}