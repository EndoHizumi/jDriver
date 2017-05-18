package jp.wsf.jDriver.test;

import jp.wsf.jDriver.annotations.CallRefrectionTestOnly;

public class Human {
private String name= "yamada";
private int age = 16;
private String gender = "female";

public Human(){
}

public Human(String name){
	this.name = name;
}

public Human(String name,int age){
	this.name = name;
	this.age = age;
}

public Human(String name,int age,String gender){
	this.name = name;
	this.age = age;
	this.gender =gender;
}

@CallRefrectionTestOnly
private void setName(String name){
	this.name = name;
}

public void setName(String firstname,String familyname){
	this.name = firstname;
}

public void setName(String firstname,String familyname,String middlename){
	this.name = firstname;
}

@CallRefrectionTestOnly
private String getName(){
	return name;
}

public void setGender(String gender){
	this.gender = gender;
}

public String getGender(){
	return gender;
}

public void setAge(int age){
	this.age = age;
}

@CallRefrectionTestOnly
private int getAge(){
	return age;
}

}
