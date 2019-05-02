#ifndef PERSISTENCIA_H
#define PERSISTENCIA_H

#include <SPI.h>
#include <SD.h>
#include "Status.h"

class Persistencia
{
public:
        Persistencia(String nomeArquivo, uint8_t pino, Status status);
        void inicializar(void);
        bool salvar(String strHTTPQueryString);
        void listar(void);

private:
      File arquivo;
      Status status;
      String nomeArquivo;
      uint8_t pino;
};

#endif
