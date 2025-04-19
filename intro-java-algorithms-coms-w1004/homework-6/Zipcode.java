
// Building Zipcode.java

public class Zipcode {

    public String zip;
    public String code;

    public Zipcode(int postalCode) {
        // Store the zip as String
        zip = Integer.toString(postalCode);
    }

    public Zipcode(String barCode) {
        // Store the code as String
        code = barCode;
    }

    public String getZIPcode() {
        // Given a String postal bar code e.g. (without the spaces) "| |:|:: :|:|: ||::: :::|| :|::| :::|| |", returns a String zip code e.g. "95014"
        String output = "";
        // Iterate through the postal bar code by digit (i.e., groups of 5 characters)...
        // ...starting after initial frame bar (first char) and ending before the final frame bar (last char)
        for (int i = 1; i < this.code.length() - 6; i += 5) {
            int digit = 0;
            //System.out.println("Segment being processed is " + this.code.charAt(i) + this.code.charAt(i+1) + this.code.charAt(i+2) + this.code.charAt(i+3) + this.code.charAt(i+4));
            // Iterate through each character in group and sum products to determine the zipcode digit
            for (int j = 0; j < 5; j++) {
                int factor = 0;
                if (Character.toString(this.code.charAt(i+j)).equals("|")) {
                    factor = 1;
                }
                if (j == 0) {
                    digit += 7*factor;
                } else if (j == 1) {
                    digit += 4*factor;
                } else if (j == 2) {
                    digit += 2*factor;
                } else if (j == 3) {
                    digit += 1*factor;
                }
            }
            //System.out.println("Current zipcode is " + output);
            //System.out.println("Digit to be added to zipcode is " + digit);
            // Build zipcode digit by digit
            if (digit > 9) {
                output += "0";
            } else {
                output += digit;
            }
        }
        // Returns completed zipcode
        return output;
    }

    public String getBarcode() {
        // Given a String a zip code e.g. "95014" returns a String postal bar code e.g. "||:|:::|:|:||::::::||:|::|:::|||"
        // Instantiates barcode with frame bar, as that is always the initial character
        String output = "|";
        int digitSum = 0;
        for (int i = 0; i < this.zip.length(); i++) {
            String digit = Character.toString(this.zip.charAt(i));
            output += getDigitCode(digit);
            digitSum += Integer.parseInt(digit);
        }
        int checkDigit = 10 - digitSum % 10;
        output += getDigitCode(Integer.toString(checkDigit)) + "|";
        return output;
    }

    public String getDigitCode(String digit) {
        // Given a digit character (i.e. 0-9) returns corresponding postal barcode segment
        if (digit.equals("0")) {
            return "||:::";
        } else if (digit.equals("1")) {
            return ":::||";
        } else if (digit.equals("2")) {
            return "::|:|";
        } else if (digit.equals("3")) {
            return "::||:";
        } else if (digit.equals("4")) {
            return ":|::|";
        } else if (digit.equals("5")) {
            return ":|:|:";
        } else if (digit.equals("6")) {
            return ":||::";
        } else if (digit.equals("7")) {
            return "|:::|";
        } else if (digit.equals("8")) {
            return "|::|:";
        } else if (digit.equals("9")) {
            return "|:|::";
        }
        return "";
    }

    public static void main(String[] args) {
        
    }

}