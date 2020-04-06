package com.lab.fifth;

import com.lab.first.Eji;
import com.lab.Transformer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Izomorfizm {

    final static int ZN = 4;
    final static int ZE = 4;

    public static void main(String[] args) {
        if (izomorfizm())
            System.out.println("its work");
        else System.out.println("its bad");

    }

    public static void Pidvis(Eji[] Graf) {
        for (int i = 0; i < ZN; i++) {
            int v = i, zm;
            //if(i>0) v++;
            for (int d = 0; d < ZE; d++) {
                if ((Graf[d].A == i || Graf[d].B == i) && (Graf[d].A > i || Graf[d].B > i)) {
                    //v++;
                    if (Graf[d].A == i)
                        zm = Graf[d].B;
                    else
                        zm = Graf[d].A;
                    for (int e = 0; e < ZE; e++) {
                        if (Graf[e].A == zm && Graf[e].B == v) {
                            Graf[e].A = v;
                            Graf[e].B = zm;
                        } else if (Graf[e].B == zm && Graf[e].A == v) {
                            Graf[e].A = zm;
                            Graf[e].B = v;
                        } else if (Graf[e].A == zm) {
                            Graf[e].A = v;
                        } else if (Graf[e].B == zm) {
                            Graf[e].B = v;
                        } else if (Graf[e].A == v) {
                            Graf[e].A = zm;
                        } else if (Graf[e].B == v) {
                            Graf[e].B = zm;
                        }
                    }
                }
            }
        }
    }


    public static boolean izomorfizm() {
        boolean[][] oneInc = new boolean[ZN][ZN];
        boolean[][] twoInc = new boolean[ZN][ZN];
        Eji[] oneGraf = new Eji[ZE];
        Eji[] twoGraf = new Eji[ZE];
        char textOut, textIn;

        for (int i = 0; i < ZN; i++)
            for (int j = 0; j < ZN; j++) {
                oneInc[i][j] = false;
                twoInc[i][j] = false;
            }
        try (FileReader reader = new FileReader("C:\\Users\\andri\\Desktop\\DMCAD\\src\\com\\lab\\fifth\\iso1")) {
            int i = 0;
            int c;
            int numbOut = 0, numbIn = 0;
            while ((c = reader.read()) != -1) {


                if ((char) c == '\n') {


                    oneGraf[i] = new Eji(numbOut, numbIn);
                    i++;
                } else if (c >= 65) {

                    numbOut = Transformer.upperLetterToNum((char) c);


                    while ((c = reader.read()) < 65) ;
                    numbIn = -1;
                    numbIn = Transformer.upperLetterToNum((char) c);

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ZE; i++) {
            oneInc[oneGraf[i].A][oneGraf[i].B] = true;
            oneInc[oneGraf[i].B][oneGraf[i].A] = true;
        }
        try (FileReader reader = new FileReader("C:\\Users\\andri\\Desktop\\DMCAD\\src\\com\\lab\\fifth\\iso2")) {
            int i = 0;
            int c;
            int numbOut = 0, numbIn = 0;
            while ((c = reader.read()) != -1) {


                if ((char) c == '\n') {


                    twoGraf[i] = new Eji(numbOut, numbIn);
                    i++;
                } else if (c >= 65) {

                    numbOut = Transformer.upperLetterToNum((char) c);


                    while ((c = reader.read()) < 65) ;
                    numbIn = -1;
                    numbIn = Transformer.upperLetterToNum((char) c);

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < ZE; i++) {
            twoInc[twoGraf[i].A][twoGraf[i].B] = true;
            twoInc[twoGraf[i].B][twoGraf[i].A] = true;
        }

        for (int i = 0; i < ZN; i++)
            for (int j = 0; j < ZN; j++)
                if (oneInc[i][j] == twoInc[i][j])
                    if (i == ZN - 1 && j == ZN - 1)
                        return true;
                    else
                        continue;
                else
                    break;
        for (int i = 0; i < ZN; i++)
            for (int j = 0; j < ZN; j++)
                oneInc[i][j] = false;

        Pidvis(oneGraf);

        for (int i = 0; i < ZE; i++) {
            oneInc[oneGraf[i].A][oneGraf[i].B] = true;
            oneInc[oneGraf[i].B][oneGraf[i].A] = true;
        }

        for (int jc = 0; jc < ZN; jc++) {
            for (int i = 0; i < ZN; i++)
                for (int j = 0; j < ZN; j++)
                    twoInc[i][j] = false;

            Pidvis(twoGraf);

            for (int i = 0; i < ZN; i++)
                for (int j = 0; j < ZN; j++)
                    if (oneInc[i][j] == twoInc[i][j])
                        if (i == ZN - 1 && j == ZN - 1)
                            return true;
                        else
                            continue;
                    else
                        break;

            for (int i = 0; i < ZE; i++) {
                twoInc[twoGraf[i].A][twoGraf[i].B] = true;
                twoInc[twoGraf[i].B][twoGraf[i].A] = true;
            }

        }

        return true;
    }

}
