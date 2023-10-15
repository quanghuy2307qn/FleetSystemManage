package com.fleetsystem.hr.models;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fleetsystem.parameters.models.CommonObject;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EmployeeStatus extends CommonObject {

}
