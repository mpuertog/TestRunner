package co.edu.uniandes.testrunner.web.persistance.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the TEST_DETAIL database table.
 * 
 */
@Entity
@Table(name = "ANDROID_EMULATOR")
@NamedQueries({ @NamedQuery(name = "AndroidEmulator.findAll", query = "SELECT t FROM AndroidEmulator t"), })

@XmlRootElement
public class AndroidEmulator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OS_CODENAME")
	private String osCodename;

	@Column(name = "ANDROID_VERSION")
	private int Androidversion;

	@Column(name = "COMMAND")
	private String command;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOsCodename() {
		return osCodename;
	}

	public void setOsCodename(String osCodename) {
		this.osCodename = osCodename;
	}

	public int getAndroidversion() {
		return Androidversion;
	}

	public void setAndroidversion(int androidversion) {
		Androidversion = androidversion;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return this.name + " - " + this.Androidversion;
	}

}
