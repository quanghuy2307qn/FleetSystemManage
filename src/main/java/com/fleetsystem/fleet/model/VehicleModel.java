package com.fleetsystem.fleet.model;

import javax.persistence.Entity;

import com.fleetsystem.parameters.models.CommonObject;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {

}
