import java.util.Scanner;

public class EXP2_S4_Grupo19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        String[][] reservas = new String[4][64]; // 4 matrices de 8x8 asientos para cada evento
        String[] fechas = {"2024-03-23 - Campo de Batalla", "2024-03-24 - Tsunami", "2024-03-25 - La Chilena", "2024-03-26 - Paraíso"};
        double totalGastado = 0; // gasto total

        // dejar las reservas como disponibles (0)
        for (int i = 0; i < reservas.length; i++) {
            for (int j = 0; j < 64; j++) {
                reservas[i][j] = "0";
            }
        }

        while (continuar) {
            int opcion;
            for (opcion = 0; opcion < 1 || opcion > 2; ) {
                System.out.println("Menú principal:");
                System.out.println("1. Comprar entrada");
                System.out.println("2. Salir");
                System.out.print("Elige una opción: ");
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > 2) {
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
                }
            }

            if (opcion == 1) {
                System.out.println("Las fechas disponibles son:");
                for (int i = 0; i < fechas.length; i++) {
                    System.out.println((i + 1) + ". " + fechas[i]);
                }

                System.out.print("¿Qué Fecha desea reservar? Elige 1, 2, 3, 4: ");
                int fechaSeleccionada = scanner.nextInt() - 1; // Ajustamos -1 para el índice del arreglo

                if (fechaSeleccionada >= 0 && fechaSeleccionada < fechas.length) {
                    System.out.println("Elige (1) ver los asientos disponibles, o (2) reservar un asiento:");
                    int accion = scanner.nextInt();

                    if (accion == 1) {
                        //mostrar asientos disponibles
                        System.out.println("Asientos disponibles:");
                        for (int i = 0; i < 8; i++) { // 8 filas
                            for (int j = 0; j < 8; j++) { // 8 columnas
                                System.out.print(reservas[fechaSeleccionada][i * 8 + j] + " ");
                            }
                            System.out.println(); // Nueva línea al final de la fila
                        }
                    } else if (accion == 2) {
                        System.out.println("Elige fila (1-8):");
                        int fila = scanner.nextInt();
                        System.out.println("Elige columna (1-8):");
                        int columna = scanner.nextInt();

                        if (fila >= 1 && fila <= 8 && columna >= 1 && columna <= 8) {
                            int indice = (fila - 1) * 8 + (columna - 1);
                            if (reservas[fechaSeleccionada][indice].equals("0")) {
                                double precioEntrada = 0;
                                String zona = "";

                                // determinar zona y precio basado en la ubicación del asiento
                                if ((columna <= 2 || columna >= 7) && fila <= 7) { // Palcos
                                    precioEntrada = 7200;
                                    zona = "Palco";
                                } else if (columna >= 3 && columna <= 6 && fila <= 4) { // Platea Alta
                                    precioEntrada = 11000;
                                    zona = "Platea Alta";
                                } else if (columna >= 3 && columna <= 6 && fila >= 5 && fila <= 7) { // Platea Baja
                                    precioEntrada = 19000;
                                    zona = "Platea Baja";
                                } else if (fila >= 7) { // VIP
                                    precioEntrada = 25000;
                                    zona = "VIP";
                                }

                                if (precioEntrada > 0) {
                                    // tipo de entrada
                                    System.out.println("Selecciona el tipo de entrada: 1. Público general 2. Estudiante (10% descuento) 3. Adulto mayor (15% descuento)");
                                    int tipoEntrada = scanner.nextInt();
                                    double descuento = 0;

                                    switch (tipoEntrada) {
                                        case 2:
                                            descuento = 0.10;
                                            break;
                                        case 3:
                                            descuento = 0.15;
                                            break;
                                    }

                                    double montoDescuento = precioEntrada * descuento;
                                    precioEntrada -= montoDescuento;

                                    // marcar reserva y mostrar mapa actualizado
                                    reservas[fechaSeleccionada][indice] = "1";
                                    System.out.println("Asientos actualizados (tu selección está marcada con 1):");
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < 8; j++) {
                                            System.out.print(reservas[fechaSeleccionada][i * 8 + j] + " ");
                                        }
                                        System.out.println();
                                    }

                                    totalGastado += precioEntrada;
                                    System.out.println("Compra realizada con éxito en zona " + zona + ".");
                                    System.out.println("Precio de la entrada con descuento: $" + precioEntrada);
                                } else {
                                    System.out.println("Ubicación de asiento no válida.");
                                }

                            } else {
                                System.out.println("Asiento no disponible.");
                            }
                        } else {
                            System.out.println("Fila o columna inválida.");
                        }
                    } else {
                        System.out.println("Acción inválida.");
                    }
                } else {
                    System.out.println("Fecha inválida.");
                }
            } else if (opcion == 2) {
                continuar = false;
            }

            if (continuar) {
                System.out.println("¿Deseas realizar otra operación? (si/no)");
                String respuesta = scanner.next();
                if (respuesta.equalsIgnoreCase("no")) {
                    continuar = false;
                }
            }
        }

        System.out.println("El total es de: $" + totalGastado + " en entradas.");
        System.out.println("Gracias por venir a Teatro Moro. ¡Hasta pronto!");
        scanner.close();
    }
}
