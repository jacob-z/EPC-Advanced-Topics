/******************************************************************************
 * Author: Jacob Zimmer
 *
 * Example lets students interact with rsa encryption and decryption in a more
 * understandable way than seeing the raw code might.  This code will not
 * compile because its dependencies are not publicly available.  Students 
 * should run the accompanying jar files, "rsa.jar" and "rsaSafe.jar" to see
 * how this program works.  The current version of this code corresponds to
 * rsaSafe.jar, but is very similar to rsa.jar.
 * 
 * java -jar rsaSafe.jar
 *
 *****************************************************************************/
import java.math.BigInteger;
import java.util.Scanner;

public class Example {

    private static final String setPlain = "\033[0;0m";
    private static final String setBold  = "\033[0;1m";
    private static final int defaultKeyBits = 1024;

    // Part of this class requires me to use a random generating function
    // with a random 32 bit key, so I declare the key here and then 
    // initialize the PseudoRandom Generating function, PRGen with the key.
    private static byte[] key = "NOTaGOODpasswordNOTaGOODpassword".getBytes();
    private static PRF prf = new PRF(key);
    private static PRGen prg; // = new PRGen(key);

    // The next step is to initialize an RSAKeyPair object.  The numeric
    // argument is the number of bits I want to use for the key, generally
    // larger keys will be more secure.
    private static RSAKeyPair rsaKP;
    private static RSAKey publicKey;
    private static RSAKey privateKey;

    // Use a string as a password for the random generation.
    private static void seedHash(String random) {
        random = random.trim();
        prg = new PRGen(prf.eval(random.getBytes()));
    }

    // For nice printing.
    private static String bold(String content) {
        return setBold + content + setPlain;
    }

    // Convert the string text to a numeric encrypted version.
    private static void encryptMessage(String plaintext) {
        try {
            byte[] p = plaintext.getBytes();
            byte[] cypher = publicKey.encrypt(p, prg);
            BigInteger c = HW2Util.bytesToBigInteger(cypher);
            System.out.println("\n" + c + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("\nUnable to encrypt.");
            System.out.println("Size limit of input exceeded.");
        } 
    }

    // Convert the numeric encrypted text back to a string.
    private static void decryptMessage(String cyphertext) {
        try {
            BigInteger cypher = new BigInteger(cyphertext);
            byte[] c = HW2Util.bigIntegerToBytes(cypher, cypher.bitLength());
            byte[] p = privateKey.decrypt(c);
            System.out.println("\n" + new String(p) + "\n");
        } catch (NumberFormatException e) {
            System.out.println("\nUnable to decrypt.");
            System.out.println("Cyphertext not in expected format.");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("\nUnable to decrypt.");
            System.out.println("Cyphertext may have been corrupted.");
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("\nWelcome to the safe version of RSA.");
        System.out.println("Begin by entering a password to use " +
                           "for random number generation: ");
        seedHash(in.nextLine());

        System.out.print(bold("\nPlease enter number of bits to use per key: "));
        String keyBits = in.nextLine();

        int numKeyBits;
        try {
            numKeyBits = Integer.parseInt(keyBits);
        } catch (NumberFormatException e) {
            System.out.println("You have entered an invalid number.");
            System.out.println("Using the default value: " + defaultKeyBits);
            numKeyBits = defaultKeyBits;
        }

        rsaKP = new RSAKeyPair(prg, numKeyBits);
        publicKey = rsaKP.getPublicKey();
        privateKey = rsaKP.getPrivateKey();

        // We can check which primes were used in generating the key pair
        BigInteger p = rsaKP.getPrimes()[0];
        BigInteger q = rsaKP.getPrimes()[1];
        System.out.println(bold("\nPrimes used in key generation:"));
        System.out.println("\n" + p + "\n\n" + q + "\n");
        System.out.println(bold("Modulus:"));
        System.out.println("\n" + publicKey.getModulus() + "\n");
        System.out.println(bold("Public Key:"));
        System.out.println("\n" + publicKey.getExponent() + "\n");
        System.out.println(bold("Private Key:"));
        System.out.println("\n" + privateKey.getExponent() + "\n");


        System.out.println("We can now send and receive encrypted messages.");
        while (true) {

            System.out.println("---------------------------------------------" +
                               "-------------------------");
            System.out.println(bold("Please enter \"enc\" to encrypt or" +
                               " \"dec\" to decrypt. Use \"quit\" to exit."));

            String response = in.nextLine();
            response = response.trim().toLowerCase();

            if (response.startsWith("enc")) {
                System.out.print(bold("Message to encrpyt: "));
                encryptMessage(in.nextLine());


            } else if (response.startsWith("dec")) {
                System.out.print(bold("Message to decrpyt: "));
                decryptMessage(in.nextLine());

            } else if (response.startsWith("quit")) {
                System.out.println("Exiting...");
                System.exit(1);
            } else {
                System.out.println("You have entered an invalid choice.");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    }
}