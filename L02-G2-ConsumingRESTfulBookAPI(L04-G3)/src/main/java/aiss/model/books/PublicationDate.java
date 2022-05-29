package aiss.model.books;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PublicationDate {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "year", "month", "monthValue", "dayOfMonth", "dayOfWeek", "era", "dayOfYear", "leapYear" })
	@Generated("jsonschema2pojo")

	@JsonProperty("year")
	private String year;
	@JsonProperty("month")
	private String month;
	@JsonProperty("monthValue")
	private String monthValue;
	@JsonProperty("dayOfMonth")
	private String dayOfMonth;
	@JsonProperty("dayOfWeek")
	private String dayOfWeek;
	@JsonProperty("era")
	private String era;
	@JsonProperty("dayOfYear")
	private String dayOfYear;
	@JsonProperty("leapYear")
	private String leapYear;

	@JsonProperty("year")
	public String getYear() {
		return year;
	}

	@JsonProperty("year")
	public void setYear(String year) {
		this.year = year;
	}

	@JsonProperty("month")
	public String getMonth() {
		return month;
	}

	@JsonProperty("month")
	public void setMonth(String month) {
		this.month = month;
	}

	@JsonProperty("monthValue")
	public String getMonthValue() {
		return monthValue;
	}

	@JsonProperty("monthValue")
	public void setMonthValue(String monthValue) {
		this.monthValue = monthValue;
	}

	@JsonProperty("dayOfMonth")
	public String getDayOfMonth() {
		return dayOfMonth;
	}

	@JsonProperty("dayOfMonth")
	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@JsonProperty("dayOfWeek")
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	@JsonProperty("dayOfWeek")
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@JsonProperty("era")
	public String getEra() {
		return era;
	}

	@JsonProperty("era")
	public void setEra(String era) {
		this.era = era;
	}

	@JsonProperty("dayOfYear")
	public String getDayOfYear() {
		return dayOfYear;
	}

	@JsonProperty("dayOfYear")
	public void setDayOfYear(String dayOfYear) {
		this.dayOfYear = dayOfYear;
	}

	@JsonProperty("leapYear")
	public String getLeapYear() {
		return leapYear;
	}

	@JsonProperty("leapYear")
	public void setLeapYear(String leapYear) {
		this.leapYear = leapYear;
	}

}
