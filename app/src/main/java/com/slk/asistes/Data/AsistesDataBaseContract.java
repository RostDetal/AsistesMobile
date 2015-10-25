package com.slk.asistes.Data;

import android.provider.BaseColumns;

/**
 * Created by VIS on 23.10.2015.
 */
public final class AsistesDataBaseContract {
    public AsistesDataBaseContract(){}


    public static abstract class BrandEntry implements BaseColumns {
        public static final String TABLE_NAME = "brands";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_POPULAR = "popular";
    }

    public static abstract class ModelEntry implements BaseColumns {
        public static final String TABLE_NAME = "models";
        public static final String COLUMN_NAME_BRAND_ID = "brand_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CODE = "code";
    }


    public static abstract class ModificationEntry implements BaseColumns {
        public static final String TABLE_NAME = "modifications";

        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_UPDATED_AT = "updated_at";
        public static final String COLUMN_NAME_MODEL_ID = "model_id";
        public static final String COLUMN_NAME_YEAR_FROM = "year_from";
        public static final String COLUMN_NAME_YEAR_TO = "year_to";
        public static final String COLUMN_NAME_ENGINE_TYPE = "engine_type";
        public static final String COLUMN_NAME_BODY_TITLE = "body_title";
        public static final String COLUMN_NAME_BODY_CODE = "body_code";
        public static final String COLUMN_NAME_BODY_DOORS = "body_doors";
        public static final String COLUMN_NAME_BODY_PLACE = "body_place";
        public static final String COLUMN_NAME_BODY_LENGTH = "body_length";
        public static final String COLUMN_NAME_BODY_WIDTH = "body_width";
        public static final String COLUMN_NAME_BODY_HEIGHT = "body_height";
        public static final String COLUMN_NAME_WHEELBASE = "wheelbase";
        public static final String COLUMN_NAME_FRONT_TRACK = "front_track";
        public static final String COLUMN_NAME_REAR_TRACK = "rear_track";
        public static final String COLUMN_NAME_ENGINE_CAPACITY = "engine_capacity";
        public static final String COLUMN_NAME_ENGINE_POWER = "engine_power";
        public static final String COLUMN_NAME_ENGINE_REVS = "engine_revs";
        public static final String COLUMN_NAME_TORQUE = "torque";
        public static final String COLUMN_NAME_POWER_SYSTEM = "power_system";
        public static final String COLUMN_NAME_GAS = "gas_distribution";
        public static final String COLUMN_NAME_LOCATION_CYLINDERS = "location_cylinders";
        public static final String COLUMN_NAME_NUMBER_CYLINDERS = "number_cylinders";
        public static final String COLUMN_NAME_COMPRESSION_RATIO = "compression_ratio";
        public static final String COLUMN_NAME_FUEL = "fuel";
        public static final String COLUMN_NAME_GEARBOX_TYPE = "gearbox_type";
        public static final String COLUMN_NAME_NUMBER_GEARS = "number_gears";
        public static final String COLUMN_NAME_NUMBER_GEARS_MECH = "number_gears_mech";
        public static final String COLUMN_NAME_DRIVE = "drive";
        public static final String COLUMN_NAME_FRONT_BRAKES = "front_brakes";
        public static final String COLUMN_NAME_REAR_BRAKES = "rear_brakes";
        public static final String COLUMN_NAME_MAXIMUM_SPEED = "maximum_speed";
        public static final String COLUMN_NAME_ACCELERATION_TIME = "acceleration_time";
        public static final String COLUMN_NAME_CURB_VEHICLE = "curb_vehicle";
        public static final String COLUMN_NAME_FUEL_TANK = "fuel_tank";
        public static final String COLUMN_NAME_TIRES_SIZE = "tires_size";
        public static final String COLUMN_NAME_ENVIRONMENTAL_STANDARD = "environmental_standard";
        public static final String COLUMN_NAME_GROUND_CLEARANCE = "ground_clearance";
        public static final String COLUMN_NAME_NUMBER_VALVES_CYLINDER = "number_valves_cylinder";
        public static final String COLUMN_NAME_FRONT_SUSPENSION = "type_front_suspension";
        public static final String COLUMN_NAME_TYPE_REAR_SUSPENSION = "type_rear_suspension";
        public static final String COLUMN_NAME_NUMBER_GEARS_AUTOMATIC = "number_gears_automatic";
        public static final String COLUMN_NAME_MAX_AMOUNT_TRUNK = "max_amount_trunk";
        public static final String COLUMN_NAME_MIN_AMOUNT_TRUNK = "min_amount_trunk";
        public static final String COLUMN_NAME_TURBOCHARGING = "turbocharging";
        public static final String COLUMN_NAME_CYLINDER_DIAMETER = "cylinder_diameter";
        public static final String COLUMN_NAME_PISTON_STROKE = "piston_stroke";
        public static final String COLUMN_NAME_GEAR_RATIO_MAIN_PAIR = "gear_ratio_main_pair";
        public static final String COLUMN_NAME_FUEL_CONSUMPTION_TOWN = "fuel_consumption_town";
        public static final String COLUMN_NAME_FUEL_CONSUMPTION_HIGHWAY = "fuel_consumption_highway";
        public static final String COLUMN_NAME_FUEL_CONSUMPTION_AVERAGE = "fuel_consumption_average";
        public static final String COLUMN_NAME_STEERING_TYPE = "steering_type";
        public static final String COLUMN_NAME_ENGINE_MODEL = "engine_model";
        public static final String COLUMN_NAME_MAX_WEIGHT = "max_weight";
        public static final String COLUMN_NAME_MOTOR_POWER = "motor_power";
        public static final String COLUMN_NAME_TURNING_CIRCLE = "turning_circle";
        public static final String COLUMN_NAME_DISK_SIZE = "disk_size";
        public static final String COLUMN_NAME_TOTAL_POWER = "total_power";
        public static final String COLUMN_NAME_ENGINE_LOCATION = "engine_location";
        public static final String COLUMN_NAME_POWER_RESERVE = "power_reserve";
        public static final String COLUMN_NAME_FULL_CHARGE = "full_charge";






    }
}
