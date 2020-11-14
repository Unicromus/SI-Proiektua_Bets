package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User extends UserBerezia implements Serializable{

	public User() {
		super();
	}

	public User(String iz,String ab1,String ab2,String erabiz,String pass,String NAN, String jd,String email,String tlf, String helb, String pstkod,String hrld, String prob,String herria) {
		this.izena = iz;
		this.abizena1 = ab1;
		this.abizena2 = ab2;
		this.erabizena = erabiz;
		this.pasahitza = pass;
		this.NAN = NAN;
		this.jaiotzedata = jd;
		this.email = email;
		this.TlfZenbakia = tlf;
		this.herrialdea = hrld;
		this.probintzia = prob;
		this.herria = herria;
		this.postakodea = pstkod;
		this.helbidea = helb;
	}

}
