import serial
import RPi.GPIO as GPIO      
import os, time

GPIO.setmode(GPIO.BOARD)    

# Enable Serial Communication
port = serial.Serial("/dev/ttyS0", baudrate=9600, timeout=1)

port.write('AT+SAPBR=3,1,"Contype","GPRS"'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+SAPBR=3,1,"APN","zap.vivo.com.br"'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+SAPBR=1,1'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+SAPBR=2,1'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+HTTPINIT'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+HTTPPARA="CID",1'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+HTTPPARA="URL","http://date.jsontest.com\"'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+HTTPACTION=0'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+HTTPTERM'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+SAPBR=0,1'+'\r')
rcv = port.read(20)
print rcv