#ifndef STATUS_H
#define STATUS_H

#include <Arduino.h>
#include "GerenteStatus.h"
#include "Semaforo.h"

class Status
{
public:
        Status(GerenteStatus gerenteStatus);

//        informar(Semaforo semaforo) {
//                switch (semaforo)
//                {
//                        case Semaforo::NORMAL:
//                                digitalWrite(5, HIGH);
//                                break;
//                        case Semaforo::ATENCAO:
//                                digitalWrite(6, HIGH);
//                                break;
//                        case Semaforo::ALERTA:
//                                digitalWrite(5, HIGH);
//                                break;
//                }
//        }

        void limpar(void);
        Semaforo getSemaforo(void);
        void setSemaforo(Semaforo semaforo);

private:
        GerenteStatus gerenteStatus;
        Semaforo semaforo;

};

#endif
