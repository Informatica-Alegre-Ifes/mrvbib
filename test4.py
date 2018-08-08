import serial
import RPi.GPIO as GPIO      
import os, time

GPIO.setmode(GPIO.BOARD)    

# Enable Serial Communication
port = serial.Serial("/dev/ttyS0", baudrate=9600, timeout=1)

port.write('AT+CSQ'+'\r\n')
rcv = port.read(200)
print rcv
time.sleep(10)

port.write('AT+CGATT=1'+'\r\n')
rcv = port.read(200)
print rcv
time.sleep(10)

port.write('AT+CIPSTART="TCP","http://date.jsontest.com",80'+'\r\n')
rcv = port.read(200)
print rcv
time.sleep(10)

port.write('AT+CIPSEND'+'\r\n')
rcv = port.read(200)
print rcv
time.sleep(10)

port.write('GET /index.html HTTP/1.1'+'\r\n')
rcv = port.read(200)
print rcv
time.sleep(10)

port.write('HOST: http://date.jsontest.com'+'\r\n\n')
rcv = port.read(200)
print rcv
time.sleep(10)

port.write("\x1A")
for i in range(10):
	rcv = port.read(10)
	print rcv
