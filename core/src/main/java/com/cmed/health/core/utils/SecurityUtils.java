package com.cmed.health.core.utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */

@Log4j2
public class SecurityUtils {

    private static SecureRandom random = new SecureRandom();
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
    private static final String SECRETE = "Secr3te-4#CoD3-Do not-Change-it-ever";
    private static final String CODE = "code";
    private static final String ORGID = "orgId";
    private static final String SURVEYID = "surveyId";
    private static final String LEAD = "lead";
    private static final String PARTICIPENTID = "participantId";


    public static String generateRandomPassword(int len) {
        String dic = ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC;
        String result = "";
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    public static String generateUniqueCode(String email, Long orgId) {
        String s = email + orgId + SECRETE;
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));

            //byte to hex
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static String generateTokenForUniqueUrl(String uniqueCode, Long orgId, Long surveyId, Long teamLeadId,
                                                   Long participantId) {
        String ptoken = CODE + "=" + uniqueCode
                + "&" + ORGID + "=" + orgId;
        if (surveyId != null) ptoken += "&" + SURVEYID + "=" + surveyId;
        if (teamLeadId != null) ptoken += "&" + LEAD + "=" + teamLeadId;
        if (participantId != null) ptoken += "&" + PARTICIPENTID + "=" + participantId;

        String encodedToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(ptoken.getBytes(StandardCharsets.UTF_8));
        return encodedToken;
    }

    public static String getUniqueCode(String encodedToken) {
        String decodedToken = getDecodedToken(encodedToken);
        String code = extractId(CODE, decodedToken);
        return code;
    }

    public static Long getOrgId(String encodedToken) {
        String decodedToken = getDecodedToken(encodedToken);
        String orgId = extractId(ORGID, decodedToken);
        if(StringUtils.isNumeric(orgId)) return Long.valueOf(orgId.trim());
        return null;
    }

    public static Long getSurveyId(String encodedToken) {
        String decodedToken = getDecodedToken(encodedToken);
        String surveyId = extractId(SURVEYID, decodedToken);
        if(StringUtils.isNumeric(surveyId)) return Long.valueOf(surveyId.trim());
        return null;
    }

    public static Long getTemLeadId(String encodedToken) {
        String decodedToken = getDecodedToken(encodedToken);
        String teamLeadId = extractId(LEAD, decodedToken);
        if(StringUtils.isNumeric(teamLeadId)) return Long.valueOf(teamLeadId.trim());
        return null;
    }

    public static Long getParticipantId(String encodedToken) {
        String decodedToken = getDecodedToken(encodedToken);
        String participantId = extractId(PARTICIPENTID, decodedToken);
        if(StringUtils.isNumeric(participantId)) return Long.valueOf(participantId.trim());
        return null;
    }

    private static String getDecodedToken(String encodedToken) {
        byte[] decodedBytes = Base64.getUrlDecoder()
                .decode(encodedToken);
        String decodedToken = new String(decodedBytes);
        return decodedToken;
    }

    private static String extractId(String param, String urlSnippet) {
        int codeStartIndex, codeEndIndex;
        codeStartIndex = codeEndIndex = -1;
        codeStartIndex = urlSnippet.indexOf(param);
        if(codeStartIndex>=0) {
            codeEndIndex = codeStartIndex + param.length();
        }

        StringBuffer sb = new StringBuffer();
        boolean firstEqualSkipped = false;
        for (int i = codeEndIndex; i < urlSnippet.length() && codeEndIndex>=1; i++) {
            if (!firstEqualSkipped && urlSnippet.charAt(i) == '=') {
                firstEqualSkipped = true;
                continue;
            }
            if (urlSnippet.charAt(i) == '&') break;
            sb.append(urlSnippet.charAt(i));
        }
        return sb.toString();
    }
}