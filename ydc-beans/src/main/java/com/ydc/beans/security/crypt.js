import Jsencrypt from 'jsencrypt';
const b64map = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';
const b64pad = '=';
const BI_RM = '0123456789abcdefghijklmnopqrstuvwxyz';
function hex2b64(h: string) {
    let i;
    let c;
    let ret: string = '';
    for (i = 0; i + 3 <= h.length; i += 3) {
        c = parseInt(h.substring(i, i + 3), 16);
        /* tslint:disable-next-line */
        ret += b64map.charAt(c >> 6) + b64map.charAt(c & 63);
    }
    if (i + 1 === h.length) {
        c = parseInt(h.substring(i, i + 1), 16);
        /* tslint:disable-next-line */
        ret += b64map.charAt(c << 2);
    }
    else if (i + 2 === h.length) {
        c = parseInt(h.substring(i, i + 2), 16);
        /* tslint:disable-next-line */
        ret += b64map.charAt(c >> 2) + b64map.charAt((c & 3) << 4);
    }
    /* tslint:disable-next-line */
    while ((ret.length & 3) > 0) {
        ret += b64pad;
    }
    return ret;
}

function int2char(n: number) {
    return BI_RM.charAt(n);
}

function b64tohex(s: string) {
    let ret = '';
    let i;
    let k = 0; // b64 state, 0-3
    let slop = 0;
    for (i = 0; i < s.length; ++i) {
        if (s.charAt(i) === b64pad) {
            break;
        }
        const v = b64map.indexOf(s.charAt(i));
        if (v < 0) {
            continue;
        }
        if (k === 0) {
            /* tslint:disable-next-line */
            ret += int2char(v >> 2);
            /* tslint:disable-next-line */
            slop = v & 3;
            k = 1;
        }
        else if (k === 1) {
            /* tslint:disable-next-line */
            ret += int2char((slop << 2) | (v >> 4));
            /* tslint:disable-next-line */
            slop = v & 0xf;
            k = 2;
        }
        else if (k === 2) {
            ret += int2char(slop);
            /* tslint:disable-next-line */
            ret += int2char(v >> 2);
            /* tslint:disable-next-line */
            slop = v & 3;
            k = 3;
        }
        else {
            /* tslint:disable-next-line */
            ret += int2char((slop << 2) | (v >> 4));
            /* tslint:disable-next-line */
            ret += int2char(v & 0xf);
            k = 0;
        }
    }
    if (k === 1) {
        /* tslint:disable-next-line */
        ret += int2char(slop << 2);
    }
    return ret;
}
/**
 * rsa公钥加密长字符串
 * @param str
 * @param publicKey
 * @returns {any}
 */
export function encryptPublicLong(text: string, publicKey: string) {
    const rsa = new Jsencrypt();
    rsa.setPublicKey(publicKey);
    const key = rsa.getKey();
    let ct = "";
    // RSA每次加密117bytes，需要辅助方法判断字符串截取位置
    // 1.获取字符串截取点
    const bytes = new Array();
    bytes.push(0);
    let byteNo = 0;
    const len = text.length;
    let c;
    let temp = 0;
    for (let i = 0; i < len; i++) {
        c = text.charCodeAt(i);
        if (c >= 0x010000 && c <= 0x10FFFF) { // 特殊字符，如Ř，Ţ
            byteNo += 4;
        }
        else if (c >= 0x000800 && c <= 0x00FFFF) { // 中文以及标点符号
            byteNo += 3;
        }
        else if (c >= 0x000080 && c <= 0x0007FF) { // 特殊字符，如È，Ò
            byteNo += 2;
        }
        else { // 英文以及标点符号
            byteNo += 1;
        }
        if ((byteNo % 117) >= 114 || (byteNo % 117) === 0) {
            if (byteNo - temp >= 114) {
                bytes.push(i);
                temp = byteNo;
            }
        }
    }
    // 2.截取字符串并分段加密
    if (bytes.length > 1) {
        for (let i = 0; i < bytes.length - 1; i++) {
            let str: any = void 0;
            if (i === 0) {
                str = text.substring(0, bytes[i + 1] + 1);
            }
            else {
                str = text.substring(bytes[i] + 1, bytes[i + 1] + 1);
            }
            const t1 = key.encrypt(str);
            ct += t1;
        }
        if (bytes[bytes.length - 1] !== text.length - 1) {
            const lastStr = text.substring(bytes[bytes.length - 1] + 1);
            ct += key.encrypt(lastStr);
        }
        return (hex2b64(ct));
    }
    const t = key.encrypt(text);
    return hex2b64(t);
}

/**
 * rsa私钥解密长字符串
 * @param str
 * @param privateKey
 * @returns {any}
 */
export function decryptPrivateLong(text: string, privateKey: string) {
    const rsa = new Jsencrypt();
    rsa.setPrivateKey(privateKey);
    const key = rsa.getKey();
    text = b64tohex(text);
    /* tslint:disable-next-line */
    const maxLength = ((key.n.bitLength() + 7) >> 3);
    try {
        if (text.length > maxLength) {
            let tempstr = "";
            const lt = text.match(/.{1,256}/g);
            if (lt) {
                lt.forEach((entry: any) => {
                    const t1 = key.decrypt(entry);
                    tempstr += t1;
                });
            }
            return tempstr;
        }
        const y = key.decrypt(text);
        return y;
    }
    catch (ex) {
        return false;
    }
}