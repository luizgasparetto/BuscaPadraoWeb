package buscapadraoweb;

import buscaweb.CapturaRecursosWeb;
import java.util.ArrayList;

public class Main {

    public static int get_char_ref (char[] vet, char ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i] == ref){
                return i;
            }
        }
        return -1;
    }

    public static int get_string_ref (String[] vet, String ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i].equals(ref)){
                return i;
            }
        }
        return -1;
    }

    public static int proximo_estado(char[] alfabeto, int[][] matriz,int estado_atual,char simbolo){
        int simbol_indice = get_char_ref(alfabeto, simbolo);
        if (simbol_indice != -1){
            return matriz[estado_atual][simbol_indice];
        }else{
            return -1;
        }
    }

    public static void main(String[] args) {
        CapturaRecursosWeb crw = new CapturaRecursosWeb();
        crw.getListaRecursos().add("https://www.tjsc.jus.br/contatos/itapema");
        crw.getListaRecursos().add("https://www.advocaciaanilsonsoares.com.br/");
        crw.getListaRecursos().add("https://blumenau.ufsc.br/2024/06/13/telefones-locais-com-ddd-47-deixarao-de-funcionar-temporariamente-a-partir-de-18-de-junho/");
        ArrayList<String> listaCodigos = crw.carregarRecursos();

        char[] alfabeto = new char[14];
        alfabeto[0] = '(';
        alfabeto[1] = ')';
        alfabeto[2] = '0';
        alfabeto[3] = '1';
        alfabeto[4] = '2';
        alfabeto[5] = '3';
        alfabeto[6] = '4';
        alfabeto[7] = '5';
        alfabeto[8] = '6';
        alfabeto[9] = '7';
        alfabeto[10] = '8';
        alfabeto[11] = '9';
        alfabeto[12] = ' ';
        alfabeto[13] = '-';

        String[] estados = new String[21];
        for (int i = 0; i <= 20; i++){
            estados[i] = "q" + i;
        }

        String estado_inicial = "q0";

        String[] estados_finais = new String[1];
        estados_finais[0] = "q20";

        int[][] matriz = new int[21][14];
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 14; j++)
                matriz[i][j] = -1;

        matriz[get_string_ref(estados, "q0")][get_char_ref(alfabeto, '(')] = get_string_ref(estados, "q1");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q1")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q2");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q2")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q3");

        matriz[get_string_ref(estados, "q3")][get_char_ref(alfabeto, ')')] = get_string_ref(estados, "q4");

        matriz[get_string_ref(estados, "q4")][get_char_ref(alfabeto, ' ')] = get_string_ref(estados, "q5");

        for (char c = '0'; c <= '8'; c++)
            matriz[get_string_ref(estados, "q5")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q6");
        matriz[get_string_ref(estados, "q5")][get_char_ref(alfabeto, '9')] = get_string_ref(estados, "q10");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q6")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q7");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q7")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q8");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q8")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q9");

        matriz[get_string_ref(estados, "q9")][get_char_ref(alfabeto, '-')] = get_string_ref(estados, "q16");

        matriz[get_string_ref(estados, "q10")][get_char_ref(alfabeto, ' ')] = get_string_ref(estados, "q11");
        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q10")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q12");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q11")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q12");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q12")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q13");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q13")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q14");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q14")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q15");

        matriz[get_string_ref(estados, "q15")][get_char_ref(alfabeto, '-')] = get_string_ref(estados, "q16");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q16")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q17");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q17")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q18");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q18")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q19");

        for (char c = '0'; c <= '9'; c++)
            matriz[get_string_ref(estados, "q19")][get_char_ref(alfabeto, c)] = get_string_ref(estados, "q20");

        for (int p = 0; p < listaCodigos.size(); p++){
            String codigoHTML = listaCodigos.get(p);

            int estado = get_string_ref(estados, estado_inicial);
            int estado_anterior = -1;
            ArrayList<String> palavras_reconhecidas = new ArrayList();

            String palavra = "";

            for (int i = 0; i < codigoHTML.length(); i++){

                estado_anterior = estado;
                estado = proximo_estado(alfabeto, matriz, estado, codigoHTML.charAt(i));
                if (estado == -1){
                    estado = get_string_ref(estados, estado_inicial);
                    if (get_string_ref(estados_finais, estados[estado_anterior]) != -1){
                        if (!palavra.equals("") && !palavras_reconhecidas.contains(palavra)){
                            palavras_reconhecidas.add(palavra);
                        }
                        i--;
                    }
                    palavra = "";

                }else{
                    palavra += codigoHTML.charAt(i);
                }
            }

            System.out.println("========================================");
            System.out.println("Pagina " + (p + 1) + ": " + crw.getListaRecursos().get(p));
            System.out.println("Telefones encontrados: " + palavras_reconhecidas.size());
            System.out.println("----------------------------------------");
            for (String pal : palavras_reconhecidas){
                System.out.println("  " + pal);
            }
            System.out.println();
        }
    }

}
