package br.com.geodrone.utils.validation;

public class ValidationCnpj {

    public boolean validate(Long cnpj) {
        try {
            if (cnpj == null) {
               return true;
            }

            String str = cnpj.toString();
            if (str.length() < 14) {
                for (int i = str.length(); i < 14; i++) {
                    str = "0" + str;
                }
            }

            if (str.length() > 14) {
                return false;
            }
            if ("00000000000000".equals(str)) {
                return false;
            }

            int soma = 0, dig;
            String cnpjCalc = str.substring(0,12);
            char[] charCnpj = str.toCharArray();

            /* Primeira parte */
            for( int i = 0; i < 4; i++ ) {
                if ( charCnpj[i]-48 >=0 && charCnpj[i]-48 <=9 ) {
                    soma += (charCnpj[i] - 48) * (6 - (i + 1)) ;
                }
            }
            for( int i = 0; i < 8; i++ ) {
                if ( charCnpj[i+4]-48 >=0 && charCnpj[i+4]-48 <=9 ) {
                    soma += (charCnpj[i+4] - 48) * (10 - (i + 1)) ;
                }
            }

            dig = 11 - (soma % 11);
            cnpjCalc += ( dig == 10 || dig == 11 ) ? "0" : Integer.toString(dig);

            /* Segunda parte */
            soma = 0;
            for ( int i = 0; i < 5; i++ ) {
                if ( charCnpj[i]-48 >=0 && charCnpj[i]-48 <=9 ) {
                    soma += (charCnpj[i] - 48) * (7 - (i + 1)) ;
                }
            }
            for ( int i = 0; i < 8; i++ ) {
                if ( charCnpj[i+5]-48 >=0 && charCnpj[i+5]-48 <=9 ) {
                    soma += (charCnpj[i+5] - 48) * (10 - (i + 1)) ;
                }
            }
            dig = 11 - (soma % 11);
            cnpjCalc += ( dig == 10 || dig == 11 ) ? "0" : Integer.toString(dig);

            return str.equals(cnpjCalc);
        } catch (Exception ex) {
            return false;
        }
    }

}
