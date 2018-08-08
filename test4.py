import serial
import RPi.GPIO as GPIO      
import os, time

GPIO.setmode(GPIO.BOARD)    

# Enable Serial Communication
port = serial.Serial("/dev/ttyS0", baudrate=9600, timeout=1)

port.write('AT+CSQ'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+CGATT=1'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+CIPSTART="TCP","date.jsontest.com",80'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('AT+CIPSEND'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('GET /index.html HTTP/1.1'+'\r\n')
rcv = port.read(20)
print rcv
time.sleep(10)

port.write('HOST: date.jsontest.com'+'\r\n\n')
rcv = port.read(20)
print rcv
time.sleep(10)