#include "Status.h"

Status::Status(GerenteStatus *gerenteStatus) {
        this->gerenteStatus = gerenteStatus;
        semaforo = Semaforo::NORMAL;
        pinMode(5, OUTPUT);
        pinMode(6, OUTPUT);
        pinMode(7, OUTPUT);
        limpar();
}

void
Status::limpar(void) {
        digitalWrite(5, LOW);
        digitalWrite(6, LOW);
        digitalWrite(7, LOW);
}

Semaforo
Status::getSemaforo(void) {
        return (semaforo);
}

void
Status::setSemaforo(Semaforo semaforo) {
        this->semaforo = semaforo;
}
