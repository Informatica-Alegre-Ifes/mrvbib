#include "Dado.h"
#include "Persistencia.h"
#include "Semaforo.h"
#include "Status.h"

GerenteStatus gerenteStatus = GerenteStatus.obterInstancia();
Persistencia persistencia("dado_gps.txt", 4, Status(gerenteStatus));
Dado dado(2, 3, Status(gerenteStatus));

void setup() {
        Serial.begin(9600);
        
        //status.informar(Semaforo::ATENCAO);
//        Serial.begin(9600);
//        dado.getSoftwareSerial()->begin(9600);
//        while (!Serial);
//        persistencia.inicializar();
//        status.limpar();
        //status.informar(Semaforo::NORMAL);
}

void loop() {
//        dado.construir();
//
//        if (dado.estahDisponivel()) {
//                persistencia.salvar(dado.toHTTPQueryString());
//                persistencia.listar();
//                delay(29000);
//                limparBufferSerial();
//        }
}

void limparBufferSerial() {
        while (Serial.available())
                Serial.read();
}
