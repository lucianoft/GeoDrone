package br.com.geodrone.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Classe utilit�ria para manipula��o de n�meros.
 *
 */
public class NumberUtils implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public String format(BigDecimal value, int fractionDigits) {
		String format = getFormat(fractionDigits);
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return format(value, decimalFormat);
	}

	public String format(BigDecimal value, DecimalFormat decimalFormat) {
		String result = null;
		if (value != null && decimalFormat != null) {
			result = decimalFormat.format(value);
		}
		return result;
	}
	public String  format(BigDecimal value) {
		if(value == null)
			return null;
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
		return decimalFormat.format(value).replaceAll(",",".");
	}

	public BigDecimal calculaMargemBruta(String tipoMargem, BigDecimal custoBruto, BigDecimal precoPartida){
		if(custoBruto == null || precoPartida ==null){
			return null;
		}
		if(Double.parseDouble(custoBruto.toString()) == 0 && Double.parseDouble(precoPartida.toString()) == 0){
			return  null;
		}
		if((tipoMargem==null ||  tipoMargem.equals("CBPD"))){
			BigDecimal x = (custoBruto.divide(precoPartida, 6, BigDecimal.ROUND_HALF_EVEN));
			return (new BigDecimal(100).multiply(new BigDecimal(1).subtract(x)));
		}
		else{
			BigDecimal x = (precoPartida.divide(custoBruto, 6, BigDecimal.ROUND_HALF_EVEN));
			return(new BigDecimal(100).multiply(((x).subtract(new BigDecimal(1)))));
		}
	}

	public String format(BigDecimal value, int fractionDigits, char decimalSeparator) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(decimalSeparator);
		String format = getFormat(fractionDigits);
		DecimalFormat decimalFormat = new DecimalFormat(format, symbols);
		return format(value, decimalFormat);
	}
	public String formatBigDecimal(String value, int integerDigits, int fractionDigits, char decimalSeparator) {
		if (value == null || "".equals(value)) return value;

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(decimalSeparator);
		String format = getFormat(fractionDigits);
		DecimalFormat decimalFormat = new DecimalFormat(format, symbols);
		decimalFormat.setMaximumIntegerDigits(integerDigits);
		return decimalFormat.format(new BigDecimal(value));
	}

	/**
	 *  Retorna a parte fracionaria do valor informado.
	 *
	 * @param value
	 * @return
	 */
	public BigDecimal fractionDigits(BigDecimal value) {
		BigDecimal result = null;
		if (value != null) {
			String aux = value.toString();
			int indexOf = aux.indexOf(".");
			if (indexOf > 0) {
				result = new BigDecimal(aux.substring(indexOf + 1));
			}
		}

		return result;
	}


	public String formatNumber(Long value) {
		String result = null;
		if (value != null) {
			Formatter formatter = new Formatter();
			result = formatter.format("%,d", value).toString();
		}

		return result;
	}

	public String getOnlyNumber(String value){
		if (value == null || "".equals(value)) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			if (Character.isDigit(value.charAt(i))){
				str.append((value.charAt(i)));
			}
		}
		return str.toString() != null && !str.toString().equals("") ? str.toString() : null ;
	}


	public boolean isBetween(BigDecimal value, BigDecimal valueDe, BigDecimal valueAte) {
		if (value == null || valueDe == null || valueAte == null) {
			return false;
		}
        return value.compareTo(valueDe) >= 0 && value.compareTo(valueAte) <= 0;
    }

	public boolean isBetween(Integer value, Integer valueDe, Integer valueAte) {
		if (value == null || valueDe == null || valueAte == null) {
			return false;
		}
        return value.intValue() >= valueDe.intValue() && value.intValue() <= valueAte.intValue();
    }


	public boolean isEqualsBigDecimal(
			BigDecimal value1,
			BigDecimal value2) {

		if (value1 == null) {
            return value2 == null;
		} else if (value2 == null) {
			return false;
		}

		return (value1.compareTo(value2) == 0);
	}


	public boolean isEqualsBigDecimal(
			BigDecimal value1,
			BigDecimal value2,
			int scale) {

		return isEqualsBigDecimal(round(value1, scale), round(value2, scale));
	}

	public boolean isEqualsInteger(Integer value1, Integer value2) {
		if (value1 == null) {
            return value2 == null;
		} else if (value2 == null) {
			return false;
		}

		return (value1.intValue() == value2.intValue());
	}

	public boolean isEqualsDouble(Double value1, Double value2) {
		if (value1 == null) {
			return value2 == null;
		} else if (value2 == null) {
			return false;
		}

		return (value1.doubleValue() == value2.doubleValue());
	}

	public boolean isEqualsLong(Long value1, Long value2) {
		if (value1 == null) {
            return value2 == null;
		} else if (value2 == null) {
			return false;
		}

		return (value1.longValue() == value2.longValue());
	}


	public boolean isEqualsString(String value1, String value2) {
		if (value1 == null) {
            return value2 == null;
		} else if (value2 == null) {
			return false;
		}

		return (value1.equals(value2));
	}

	public boolean isGreaterEqualsThan(BigDecimal value, BigDecimal valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
        return value.compareTo(valueDe) >= 0;
    }

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterEqualsThan(Integer value, Integer valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
        return value.intValue() >= valueDe.intValue();
    }

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterEqualsThan(Long value, Long valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
        return value.longValue() >= valueDe.longValue();
    }
	
	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterThan(BigDecimal value, BigDecimal valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
        return value.compareTo(valueDe) > 0;
    }

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterThan(Integer value, Integer valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
        return value.intValue() > valueDe.intValue();
    }

	
	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterThan(Long value, Long valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
        return value.longValue() > valueDe.longValue();
    }

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessEqualsThan(BigDecimal value, BigDecimal valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
        return value.compareTo(valueAte) <= 0;
    }

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessEqualsThan(Integer value, Integer valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
        return value.intValue() <= valueAte.intValue();
    }
	
	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessEqualsThan(Long value, Long valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
        return value.longValue() <= valueAte.longValue();
    }

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessThan(BigDecimal value, BigDecimal valueAte) {
		if (value == null || valueAte == null) {
			return false;
		}
        return value.compareTo(valueAte) < 0;
    }

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessThan(Integer value, Integer valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
        return value.intValue() < valueAte.intValue();
    }
	
	
	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessThan(Long value, Long valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
        return value.longValue() < valueAte.longValue();
    }

	/**
	 * Verifica se o value informado � um n�mero.
	 *
	 * @param value
	 * @return
	 */
	public boolean isNumber(String value) {
		return (value != null && Pattern.matches("[0-9]+", value));
	}

	/**
	 * Verifica se o value informado est� no formato de um BigDecimal.
	 *
	 * @param value
	 * @return
	 */
	public boolean isBigDecimal(String value) {
		return (value != null && Pattern.matches("[0-9]+(\\.[0-9]*){0,1}", value));
	}

	/**
	 * Verifica se o number informado � igual a "1".
	 *
	 * @param number
	 * @return
	 */
	public boolean isTrue(Number number) {
		return (number != null && number.longValue() == 1);
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>igual</u> ao valor de <u>B</u>, ou se os <u>dois</u> valores forem <u>nulos</u>; <b>false</b> caso contr�rio.
	 */
	public <I extends Comparable<I>> boolean isValueEquals(I valueA, I valueB) {
		if ((valueA == null) && (valueB == null)) {
			return true;

		} else if ((valueA == null) || (valueB == null)) {
			return false;

		} else {
			return (valueA.compareTo(valueB) == 0);
		}
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>maior ou igual</u> ao valor de <u>B</u>; <b>false</b> caso contr�rio.
	 */
	public <I extends Comparable<I>> boolean isValueGreaterEqualsThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else return valueA.compareTo(valueB) >= 0;
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>maior</u> que o valor de <u>B</u>; <b>false</b> caso contr�rio.
	 */
	public <I extends Comparable<I>> boolean isValueGreaterThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else return valueA.compareTo(valueB) > 0;
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>menor ou igual</u> ao valor de <u>B</u>; <b>false</b> caso contr�rio.
	 */
	public <I extends Comparable<I>> boolean isValueLessEqualsThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else return valueA.compareTo(valueB) <= 0;
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>menor</u> que o valor de <u>B</u>; <b>false</b> caso contr�rio.
	 */
	public <I extends Comparable<I>> boolean isValueLessThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else return valueA.compareTo(valueB) < 0;
	}

	public BigDecimal multiplyMinusOne(BigDecimal value) {
		if (value != null) {
			return value.multiply(BigDecimal.ONE.negate());
		}

		return value;
	}

	public BigDecimal negativeValue(BigDecimal value) {
		if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
			return value.negate();
		}

		return value;
	}

	public BigDecimal positiveValue(BigDecimal value) {
		if (value != null && value.compareTo(BigDecimal.ZERO) < 0) {
			return value.negate();
		}

		return value;
	}

	public Integer positiveValue(Integer value) {
		if (value != null && value.intValue() < 0) {
			value *= -1;
		}

		return value;
	}
	
	public Long positiveValue(Long value) {
		if (value != null && value.longValue() < 0) {
			value *= -1;
		}

		return value;
	}

	public BigDecimal parseBigDecimal(String value, char decimalSeparator) throws ParseException{
		
		return parseBigDecimal(value, decimalSeparator, null);
	}
	
	public BigDecimal parseBigDecimal(String value, char decimalSeparator, BigDecimal defaultValue) throws ParseException {
		
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(decimalSeparator);
		DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
		
		return new BigDecimal(decimalFormat.parse(value).toString());
	}

	public BigDecimal parseBigDecimal(String value) {
		return parseBigDecimal(value, null);
	}
	
	public BigDecimal parseBigDecimalScale(String value, Integer decimalLengh) {
		if (value != null && decimalLengh != null && value.length() >= decimalLengh) {
			value = value.substring(0, value.length() - decimalLengh) + "."
			+ value.substring(value.length() - decimalLengh);
		}
		return parseBigDecimal(value);
	}
	
	public BigDecimal parseBigDecimal(String value, BigDecimal defaultValue) {
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		if(value.contains(",")){
			 value = value.replace(',','.');
		}
		if(value.contains("R$")){
			value = value.replace("R$","");
		}
		return new BigDecimal(value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public BigDecimal parseBigDecimal(Double value) {
		if (value == null) {
			return null;
		}

		return new BigDecimal(value);
	}

	public BigInteger parseBigInteger(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}

		return new BigInteger(value);
	}

	public Integer parseIntSeNumber(String value) {
		if (isNumber(value)) {
			return parseInt(value);
		}
		return null;
	}

	public Integer parseInt(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}

		return Integer.parseInt(value);
	}

	public Integer parseInt(BigDecimal value) {
		if (value == null) {
			return null;
		}

		if (value.longValue() < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else if (value.longValue() > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return value.intValue();
		}
	}

	public Integer parseIntOnlyNumber(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		String newValue = getOnlyNumber(value);
		if (newValue == null || "".equals(newValue)) {
			return null;
		}
		return Integer.parseInt(newValue);
	}

	public Long parseLongSeNumber(String value) {
		if (isNumber(value)) {
			return parseLong(value);
		}
		return null;
	}

	public Long parseLong(String value) {
		if (isEmpty(value)) {
			return null;
		}

		return Long.parseLong(value);
	}

	public Long parseLongOnlyNumber(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		String newValue = getOnlyNumber(value);
		if (newValue == null || "".equals(newValue)) {
			return null;
		}
		return Long.parseLong(newValue);
	}

	public List<Long> parseLong(String[] values) {
		if (values == null || values.length <= 0) {
			return null;
		}

		List<Long> result = new ArrayList<Long>();
		for (String value : values) {
			result.add(parseLong(value));
		}

		return result;
	}

	public List<Long> parseLong(List<String> values) {
		if (values == null || values.isEmpty()) {
			return null;
		}

		List<Long> result = new ArrayList<Long>();
		for (String value : values) {
			result.add(parseLong(value));
		}

		return result;
	}

	public Long parseLong(BigDecimal value) {
		if (value == null) {
			return null;
		}

		if (value.longValue() < Long.MIN_VALUE) {
			return Long.MIN_VALUE;
		} else if (value.longValue() > Long.MAX_VALUE) {
			return Long.MAX_VALUE;
		} else {
			return value.longValue();
		}
	}

	public BigDecimal round(BigDecimal value, int scale) {
		BigDecimal result = null;

		if (value != null) {
			result = value.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		}

		return result;
	}

	public BigDecimal trunc(BigDecimal value, int scale) {
		BigDecimal result = null;

		if (value != null) {
			result = value.setScale(scale, BigDecimal.ROUND_DOWN);
		}

		return result;
	}

	public BigDecimal divide(BigDecimal dividendo, BigDecimal divisor, int scale) {
		BigDecimal result = null;
		if (dividendo != null && divisor != null) {
			result = dividendo.divide(divisor, scale, BigDecimal.ROUND_HALF_EVEN);
		}

		return result;
	}

	public String toString(Integer value) {
		return (value == null ? null : value.toString());
	}

	public String toString(Long value) {
		return (value == null ? null : value.toString());
	}

	public String toString(BigInteger value) {
		return (value == null ? null : value.toString());
	}

	public String toString(Double value) {
		return (value == null ? null : value.toString());
	}

	public String toString(List<Long> values) {
		
		if (values == null) return null;
		StringBuilder s = new StringBuilder();
		for (Long value : values) {
			if (s.length() > 0)
				s.append(",");
			s.append(toString(value));
		}
		
		return s.toString();
	}
	
	private String getFormat(int fractionDigits) {
		String format = "0";
		if (fractionDigits > 0) {
			format += ".";
			for (int i = 0; i < fractionDigits; i++) {
				format += "0";
			}
		}
		return format;
	}

	public long base36ToDecimal(String s) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        s = s.toUpperCase();
        long val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 36*val + d;
        }
        return val;
    }


    // precondition:  d is a nonnegative integer
    public String decimalToBase36(long d) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = (int)(d % 36);                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 36;
        }
        return hex;
    }

    public Integer coalesce(Integer value, Integer defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }

    public Long coalesce(Long value, Long defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }

    public BigDecimal coalesce(BigDecimal value, BigDecimal defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }

    public String coalesce(String value, String defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }    

    public BigDecimal add(BigDecimal... valores) {
    	return add(2, valores);
    }
    
    public BigDecimal add(int scale, BigDecimal... valores) {
    	if (valores == null)
    		return null;
    	
		BigDecimal result = BigDecimal.ZERO;
		
		for (BigDecimal valor : valores) {
			valor = coalesce(valor, BigDecimal.ZERO);
			result = result.add(valor);
		}
		return setScale(result, scale);
	}
    
	public BigDecimal add(BigDecimal value1, BigDecimal value2, int scale) {
		BigDecimal result = null;

		if (value1 != null || value2 != null) {
			result = (value1 == null ? BigDecimal.ZERO : value1);
			if (value2 != null) {
				result = result.add(value2);
			}

			if (scale >= 0) {
				result = setScale(result, scale);
			}
		}

		return result;
	}
	
	public BigDecimal subtract(BigDecimal value1, BigDecimal value2, int scale) {
		BigDecimal result = null;

		if (value1 != null || value2 != null) {
			result = (value1 == null ? BigDecimal.ZERO : value1);
			if (value2 != null) {
				result = result.subtract(value2);
			}

			if (scale >= 0) {
				result = setScale(result, scale);
			}
		}

		return result;
	}
	
	public BigDecimal setScale(BigDecimal value, int scale) {
		if (value == null) {
			return null;
		}
		return value.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}

	
	public boolean isEqualsBigInteger(BigInteger value1, BigInteger value2) {
		if (value1 == null) {
            return value2 == null;
		} else if (value2 == null) {
			return false;
		}

		return (value1.compareTo(value2) == 0);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

	/**
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public String trunc(String value, int length) {
		if (value == null) {
			return null;
		}
		if (length < 0){
			throw new StringIndexOutOfBoundsException("String index out of range: " + length + " for string " + value);
		}

		if (value.length() > length) {
			value = value.substring(0, length);
		}

		return value;
	}

	/**
	 * 
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	public BigDecimal minValue(BigDecimal valueA, BigDecimal valueB) {
		if (valueA == null) {
			return valueB;
		}
		if (valueB == null) {
			return valueA;
		}
		
		if (valueB.compareTo(valueA) < 0) {
			return valueB;
		}
		return valueA;
	}
	
	public Integer add(Integer value1, Integer value2) {
		Integer result = null;

		if (value1 != null || value2 != null) {
			result = (value1 == null ? 0 : value1);
			if (value2 != null) {
				result = result + value2;
			}
		}

		return result;
	}

	public Long add(Long value1, Long value2) {
		Long result = null;

		if (value1 != null || value2 != null) {
			result = (value1 == null ? 0 : value1);
			if (value2 != null) {
				result = result + value2;
			}
		}

		return result;
	}

	/**
	 * Converte a string para o formato HEXADECIMAL.
	 *
	 * @param value
	 * @return
	 */
	public String convertStringToHexadecimal(String value) {
		if (isEmpty(value)) {
			return value;
		}

		char[] chars = value.toCharArray();
		StringBuilder hex = new StringBuilder();
		for (char ch : chars) {
			hex.append(Integer.toHexString(ch));
		}

		return hex.toString();
	}

	public String convertHexadecimalToString(String hexadecimal) {
		if (isEmpty(hexadecimal)) {
			return hexadecimal;
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < hexadecimal.length()-1; i+=2) {
			// pega o valor hexadecimal em pares.
			String output = hexadecimal.substring(i, (i + 2));
			// converte hexadecimal para decimal.
			int decimal = Integer.parseInt(output, 16);
			// converte o decimal para caracter.
			result.append((char)decimal);
		}

		return result.toString();
	}

	/**
	 * Verifica se o n�mero informado � um n�mero "Par".
	 *
	 * @param value
	 * @return
	 */
	public boolean isNumberPar(int value) {
        return (value % 2) == 0;
    }

	/**
	 * Verifica se o n�mero informado � um n�mero "�mpar".
	 *
	 * @param value
	 * @return
	 */
	public boolean isNumberImpar(int value) {
		return !isNumberPar(value);
	}

	/*public static void main(String[] args) throws ParseException {
		try {
			NumberUtils numberUtils = new NumberUtils();
			CryptoUtils cryptoUtils = new CryptoUtils();

			String dhEmi = "2014-03-18T10:55:33-03:00"; // Sa�da : 323031342d30332d31385431303a35353a33332d30333a3030
			String digVal = "yzGYhUx1/XYYzksWB+fPR3Qc50c="; // Sa�da : 797a4759685578312f5859597a6b7357422b6650523351633530633d
			
			String dhEmiHex = numberUtils.convertStringToHexadecimal(dhEmi);
			String digValHex = numberUtils.convertStringToHexadecimal(digVal);

			System.out.println("dhEmi: [" + dhEmi + "], [" + dhEmiHex + "]");
			System.out.println("digVal: [" + digVal + "], [" + digValHex + "]");

			String paramHashQrCode = "chNFe=28140300156225000131650110000151341562040824&nVersao=100&tpAmb=1&cDest=13017959000181&dhEmi=323031342d30332d31385431303a35353a33332d30333a3030&vNF=60.90&vICMS=12.75&digVal=797a4759685578312f5859597a6b7357422b6650523351633530633d&cIdToken=000001SEU-CODIGO-CSC-CONTRIBUINTE-36-CARACTERES";
			// Sa�da: 329f9d7b9fc5650372c1b2699ab88e9e22e0d33a
			String hashQrCode = cryptoUtils.encryptSHA1Hexadecimal(paramHashQrCode);
			System.out.println("hashQrCode: [" + hashQrCode + "]");
		} catch (CryptographyException ex) {
			ex.printStackTrace();
		} 
	}*/

}