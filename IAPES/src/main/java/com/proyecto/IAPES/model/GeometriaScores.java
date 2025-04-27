package com.proyecto.IAPES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
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
    @Column(name = "Id_User")
    private Long Id_User;

    @Column(name = "AyFp_Percentage")
    private int AyFp_Percentage;

    @Column(name = "Tp_Percentage")
    private int Tp_Percentage;

    @Column(name = "St_Percentage")
    private int St_Percentage;

    @Column(name = "Gab_Percentage")
    private int Gab_Percentage;

    @Column(name = "T_Percentage")
    private int T_Percentage;

    @Column(name = "VyAs_Percentage")
    private int VyAs_Percentage;

    // Constructores
    public GeometriaScores() {}

    public GeometriaScores(Long id_User, int ayFp, int tp, int st, int gab, int t, int vyAs) {
        this.Id_User = id_User;
        this.AyFp_Percentage = ayFp;
        this.Tp_Percentage = tp;
        this.St_Percentage = st;
        this.Gab_Percentage = gab;
        this.T_Percentage = t;
        this.VyAs_Percentage = vyAs;
    }
}