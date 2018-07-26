import serial
import RPi.GPIO as GPIO      
import os, time

GPIO.setmode(GPIO.BOARD)    

# Enable Serial Communication
port = serial.Serial("/dev/ttyS0", baudrate=9600, timeout=1)

port.write('AT+CIPMUX=1'+'\r\n')
rcv = port.read(10)
print rcv
time.sleep(1)

port.write('AT+CSTT="zap.vivo.com.br","vivo","vivo"'+'\r\n')
rcv = port.read(10)
print rcv
time.sleep(1)

port.write('AT+CGATT=1'+'\r\n')
rcv = port.read(10)
print rcv
time.sleep(1)

port.write('AT+CIICR'+'\r\n')
rcv = port.read(10)
print rcv
time.sleep(1)

port.write('AT+CIFSR'+'\r\n')
rcv = port.read(10)
print rcv
time.sleep(1)

# port.write('AT'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+CGATT?'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+SAPBR=3,1,"CONTYPE","GPRS"'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+SAPBR=3,1,"APN","zap.vivo.com.br"'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# # port.write('AT+SAPBR=3,1,"USER","vivo"'+'\r\n')
# # rcv = port.read(10)
# # print rcv
# # time.sleep(1)

# # port.write('AT+SAPBR=3,1,"PWD","vivo"'+'\r\n')
# # rcv = port.read(10)
# # print rcv
# # time.sleep(1)

# port.write('AT+SAPBR=1,1'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+HTTPINIT'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+HTTPPARA="CID",1'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+HTTPPARA="URL","www.uproc.com.br/page.php?dat=788868856775757467484648464874"'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+HTTPACTION=0'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+HTTPTERM'+'\r\n')
# rcv = port.read(10)
# print rcv
# time.sleep(1)

# port.write('AT+SAPBR=0,1'+'\r')
# rcv = port.read(10)
# print rcv