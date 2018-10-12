import serial
import RPi.GPIO as GPIO      
import os, time

GPIO.setmode(GPIO.BOARD)    

# Enable Serial Communication
port = serial.Serial("/dev/ttyS0", baudrate=9600, timeout=1)

# port.write('AT+CSQ'+'\r\n')
# rcv = port.read(200)
# print rcv
# time.sleep(2)

# port.write('AT+CMEE=2'+'\r\n')
# rcv = port.read(200)
# print rcv
# time.sleep(2)

port.write('AT+CGATT=1'+'\r\n')
rcv = port.read(100)
print rcv
time.sleep(1)

port.write('AT+CGDCONT=1,"IP","zap.vivo.com.br"'+'\r\n')
rcv = port.read(100)
print rcv
time.sleep(5)

# port.write('AT+CIPSTATUS'+'\r\n')
# rcv = port.read(200)
# print rcv
# time.sleep(2)

port.write('AT+CIPSTART="TCP","201.140.234.76",8080'+'\r\n')
rcv = port.read(100)
print rcv
time.sleep(5)

# port.write('AT+CIPSTATUS'+'\r\n')
# rcv = port.read(200)
# print rcv
# time.sleep(2)

port.write('AT+CIPSEND'+'\r')
rcv = port.read(100)
print rcv
time.sleep(5)

port.write('GET /cadastroumidade.php?umidade=16 HTTP/1.0'+'\r\n')
rcv = port.read(100)
print rcv
time.sleep(1)

port.write('HOST: 201.140.234.76'+'\r\n\r\n')
rcv = port.read(12228)
print rcv
time.sleep(20)

port.write("\x1A")
rcv = port.read(12228)
print rcv
time.sleep(20)

port.write('AT+CIPSTATUS'+'\r\n')
rcv = port.read(200)
print rcv
time.sleep(2)

# port.write('AT+CIPCLOSE'+'\r\n')
# rcv = port.read(12228)
# print rcv
# time.sleep(2)

# port.write('AT+CIPSHUT'+'\r\n')
# rcv = port.read(12228)
# print rcv
# time.sleep(1)

# port.write("\x1A")
# for i in range(5):
# 	rcv = port.read(10)
# 	print rcv
