import socket

# Creează un socket UDP
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# Setează adresa și portul pe care serverul va asculta
server_address = ("127.0.0.1", 5555)
s.bind(server_address)

print("Serverul a pornit. Așteaptă mesaje...")

# Lista pentru a păstra adresele clienților
client_addresses = []

while True:
    data, address = s.recvfrom(1024)

    # Adaugă adresa la lista clienților dacă nu este deja
    if address not in client_addresses:
        print(f"S-a conectat la server: {data.decode('utf-8')}")
        client_addresses.append(address)
    else:
        print(f"Mesaj primit de la {address}: {data.decode('utf-8')}")
        # Trimite mesajul înapoi la toți clienții conectați
        for client_address in client_addresses:
            s.sendto(data, client_address)
