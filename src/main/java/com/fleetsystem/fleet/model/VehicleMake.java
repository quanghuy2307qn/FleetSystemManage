package com.fleetsystem.fleet.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fleetsystem.parameters.models.CommonObject;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VehicleMake extends CommonObject {

}
