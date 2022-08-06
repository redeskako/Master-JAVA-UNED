package com.uned.worldhealthbank.migracion;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DATA_OLD")
public class Data_Old {
	
	@EmbeddedId
	private Data_Old_Id data_Old_Id;
	
	@Column(name="PERCENTAGE", nullable = true)
	private float percentage;

	public Data_Old() {
	}

	public Data_Old(Data_Old data_Old) {
		this.data_Old_Id = data_Old.getData_Old_Id();
		this.percentage = data_Old.getPercentage();
	}

	public Data_Old(Data_Old_Id data_Old_Id, float percentage) {
		this.data_Old_Id = data_Old_Id;
		this.percentage = percentage;
	}

	public Data_Old_Id getData_Old_Id() {
		return data_Old_Id;
	}

	public void setData_Old_Id(Data_Old_Id data_Old_Id) {
		this.data_Old_Id = data_Old_Id;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Data_Old [data_Old_Id=" + data_Old_Id + ", percentage=" + percentage + "]";
	}

	
}
