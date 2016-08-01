package com.cumulocity.me.agent.fieldbus;

import com.cumulocity.me.agent.fieldbus.model.AlarmSeverity;
import com.cumulocity.me.agent.smartrest.model.TemplateCollection;
import com.cumulocity.me.agent.smartrest.model.template.*;

public class FieldbusTemplates {
    public static final String XID = "J2ME_FieldbusTemplates_v5";

    public static final int GET_CHILD_DEVICES_REQUEST_MESSAGE_ID = 100;
    public static final int MODBUS_CHILD_DEVICE_RESPONSE_MESSAGE_ID = 1000;
    public static final int CAN_CHILD_DEVICE_RESPONSE_MESSAGE_ID = 1001;

    public static final int GET_FIELDBUS_DEVICE_TYPE_REQUEST_MESSAGE_ID = 101;
    public static final int DEVICE_TYPE_NAME_RESPONSE_MESSAGE_ID = 1010;
    public static final int DEVICE_TYPE_TIME_RESPONSE_MESSAGE_ID = 1011;
    public static final int DEVICE_TYPE_COIL_RESPONSE_MESSAGE_ID = 1012;
    public static final int DEVICE_TYPE_COIL_STATUS_RESPONSE_MESSAGE_ID = 1013;
    public static final int DEVICE_TYPE_COIL_ALARM_RESPONSE_MESSAGE_ID = 1014;
    public static final int DEVICE_TYPE_COIL_EVENT_RESPONSE_MESSAGE_ID = 1015;
    public static final int DEVICE_TYPE_REGISTER_RESPONSE_MESSAGE_ID = 1021;
    public static final int DEVICE_TYPE_REGISTER_STATUS_RESPONSE_MESSAGE_ID = 1022;
    public static final int DEVICE_TYPE_REGISTER_ALARM_RESPONSE_MESSAGE_ID = 1023;
    public static final int DEVICE_TYPE_REGISTER_EVENT_RESPONSE_MESSAGE_ID = 1024;
    public static final int DEVICE_TYPE_REGISTER_MEASUREMENT_RESPONSE_MESSAGE_ID = 1025;

    public static final int GET_ACTIVE_ALARMS_REQUEST_MESSAGE_ID = 103;
    public static final int ALARMS_ARRAY_ID_TYPE_RESPONSE_MESSAGE_ID = 1030;

    public static final int CREATE_ALARM_REQUEST_MESSAGE_ID = 104;
    public static final int CREATE_ALARM_RESPONSE_MESSAGE_ID = 1040;

    public static final int CLEAR_ALARM_REQUEST_MESSAGE_ID = 105;

    public static final TemplateCollection INSTANCE = TemplateCollection.templateCollection()
            .xid(XID)
            .template(TemplateBuilder.requestTemplate()
                    .messageId(GET_CHILD_DEVICES_REQUEST_MESSAGE_ID)
                    .method(Method.GET)
                    .path(Path.path("/inventory/managedObjects/&&/childDevices"))
                    .placeholderType(PlaceholderType.UNSIGNED)
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(MODBUS_CHILD_DEVICE_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.references")
                    .jsonPath("")
                    .jsonPath("$.managedObject.id")
                    .jsonPath("$.managedObject.name")
                    .jsonPath("$.managedObject.c8y_ModbusDevice.address")
                    .jsonPath("$.managedObject.c8y_ModbusDevice.type")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(CAN_CHILD_DEVICE_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.references")
                    .jsonPath("")
                    .jsonPath("$.managedObject.id")
                    .jsonPath("$.managedObject.c8y_CanDevice.type")
            )
            .template(TemplateBuilder.requestTemplate()
                    .messageId(GET_FIELDBUS_DEVICE_TYPE_REQUEST_MESSAGE_ID)
                    .method(Method.GET)
                    .path(Path.path("&&"))
                    .placeholderType(PlaceholderType.STRING)
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_NAME_RESPONSE_MESSAGE_ID)
                    .jsonPath("")
                    .jsonPath("")
                    .jsonPath("$.name")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_TIME_RESPONSE_MESSAGE_ID)
                    .jsonPath("")
                    .jsonPath("")
                    .jsonPath("$.c8y_useServerTime")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_COIL_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Coils")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.number")
                    .jsonPath("$.name")
                    .jsonPath("$.input")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_COIL_STATUS_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Coils")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.statusMapping.status")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_COIL_ALARM_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Coils")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.alarmMapping.raiseAlarmTemplate")
                    .jsonPath("$.alarmMapping.type")
                    .jsonPath("$.alarmMapping.text")
                    .jsonPath("$.alarmMapping.severity")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_COIL_EVENT_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Coils")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.eventMapping.eventTemplate")
                    .jsonPath("$.eventMapping.type")
                    .jsonPath("$.eventMapping.text")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_REGISTER_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Registers")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.number")
                    .jsonPath("$.name")
                    .jsonPath("$.input")
                    .jsonPath("$.signed")
                    .jsonPath("$.startBit")
                    .jsonPath("$.noBits")
                    .jsonPath("$.multiplier")
                    .jsonPath("$.divisor")
                    .jsonPath("$.offset")
                    .jsonPath("$.decimalPlaces")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_REGISTER_STATUS_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Registers")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.statusMapping.status")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_REGISTER_ALARM_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Registers")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.alarmMapping.raiseAlarmTemplate")
                    .jsonPath("$.alarmMapping.type")
                    .jsonPath("$.alarmMapping.text")
                    .jsonPath("$.alarmMapping.severity")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_REGISTER_EVENT_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Registers")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.eventMapping.eventTemplate")
                    .jsonPath("$.eventMapping.type")
                    .jsonPath("$.eventMapping.text")
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(DEVICE_TYPE_REGISTER_MEASUREMENT_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.c8y_Registers")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.measurementMapping.sendMeasurementTemplate")
                    .jsonPath("$.measurementMapping.type")
                    .jsonPath("$.measurementMapping.series")
            )
            .template(TemplateBuilder.requestTemplate()
                    .messageId(GET_ACTIVE_ALARMS_REQUEST_MESSAGE_ID)
                    .method(Method.GET)
                    .path(Path.path("/alarm/alarms?source=%%&status=ACTIVE"))
                    .placeholder("%%")
                    .placeholderType(PlaceholderType.UNSIGNED)
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(ALARMS_ARRAY_ID_TYPE_RESPONSE_MESSAGE_ID)
                    .jsonPath("$.alarms")
                    .jsonPath("")
                    .jsonPath("$.id")
                    .jsonPath("$.type")
            )
            .template(TemplateBuilder.requestTemplate()
                    .messageId(CREATE_ALARM_REQUEST_MESSAGE_ID)
                    .method(Method.POST)
                    .path(Path.path("/alarm/alarms"))
                    .content("application/vnd.com.nsn.cumulocity.alarm+json")
                    .accept("application/vnd.com.nsn.cumulocity.alarm+json")
                    .placeholderType(new PlaceholderType[]{PlaceholderType.UNSIGNED, PlaceholderType.STRING, PlaceholderType.STRING, PlaceholderType.STRING, PlaceholderType.NOW})
                    .json(Json.json()
                            .addJson("source", Json.json()
                                    .addString("id", TemplateBuilder.PLACEHOLDER)
                            )
                            .addString("type", TemplateBuilder.PLACEHOLDER)
                            .addString("text", TemplateBuilder.PLACEHOLDER)
                            .addString("severity", TemplateBuilder.PLACEHOLDER)
                            .addString("time", TemplateBuilder.PLACEHOLDER)
                    )
            )
            .template(TemplateBuilder.responseTemplate()
                    .messageId(CREATE_ALARM_RESPONSE_MESSAGE_ID)
                    .jsonPath("")
                    .jsonPath("$.severity")
                    .jsonPath("$.id")
            )
            .template(TemplateBuilder.requestTemplate()
                    .messageId(CLEAR_ALARM_REQUEST_MESSAGE_ID)
                    .method(Method.PUT)
                    .path(Path.path("/alarm/alarms/&&"))
                    .content("application/vnd.com.nsn.cumulocity.alarm+json")
                    .placeholderType(PlaceholderType.UNSIGNED)
                    .json(Json.json()
                            .addString("status", "CLEARED")
                    )
            )
            .build();


}
