#include "Dado.h"

Dado::Dado(uint8_t pinoRX, uint8_t pinoTX, Status status) : status(status) {
        softwareSerial = new SoftwareSerial(pinoRX, pinoTX);
        this->status = status;
        disponivel = false;
}

SoftwareSerial *
Dado::getSoftwareSerial(void) {
        return (softwareSerial);
}

void
Dado::construir(void) {
        while (softwareSerial->available())
                gps.encode(softwareSerial->read());
        
        if (atualizou()) {
                preencher();
                disponivel = true;
        }
        else {
                disponivel = false;
        }
}

bool
Dado::estahDisponivel(void) {
        return (disponivel);
}

String
Dado::toHTTPQueryString() {
        return ("lat=" + String(latitude, 6) + "&lon=" + String(longitude, 6) + "&alt=" + altitude + "&vel=" + velocidade + "&dth=" + data + "" + hora);
}

bool
Dado::atualizou(void) {
        return (gps.location.isUpdated() && gps.altitude.isUpdated() && gps.speed.isUpdated() && gps.date.isUpdated() && gps.time.isUpdated());
}

void
Dado::preencher(void) {
        latitude = gps.location.lat();
        longitude = gps.location.lng();
        altitude = gps.altitude.meters();
        velocidade = gps.speed.kmph();
        data = gps.date.value();
        hora = gps.time.value();
}
