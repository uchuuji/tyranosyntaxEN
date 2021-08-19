"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.OutlineProvider = void 0;
const vscode = require("vscode");
class OutlineProvider {
    constructor() {
        this.regExp = /((\w+))\s*((\S*)=\"?(\w*)\"?)*()/;
        this.MATCH_TEXTS = ["if", "elsif", "else", "endif", "ignore", "endignore", "jump", "call", "button", "link", "s", "iscript", "endscript", "loadjs"];
    }
    provideDocumentSymbols(document, token) {
        let symbols = [];
        for (let i = 0; i < document.lineCount; i++) {
            let line = document.lineAt(i); //i行目のドキュメントを取得
            let match = line.text.match(this.regExp); //[hoge param=""]の形式のタグでマッチしてるかを探して変数に格納
            if (!match) {
                // return Promise.reject("unmatched."); //指定文字がなかった時。引数で与えられた理由でPromiseオブジェクトを返却
                continue;
            }
            let matchText = match[1];
            //matchTextがMATCH_TEXTSで定義したいずれかのタグがあるならアウトラインに表示
            for (let j = 0; j < this.MATCH_TEXTS.length; j++) {
                if (matchText === this.MATCH_TEXTS[j]) {
                    let symbol = new vscode.DocumentSymbol(line.text, 'Component', vscode.SymbolKind.Class, line.range, line.range);
                    symbols.push(symbol);
                }
            }
            //ラベルをアウトラインに表示
            if (line.text.startsWith("*")) {
                let symbol = new vscode.DocumentSymbol(line.text, 'Component', vscode.SymbolKind.Function, line.range, line.range);
                symbols.push(symbol);
            }
        }
        return symbols;
    }
}
exports.OutlineProvider = OutlineProvider;
//# sourceMappingURL=OutlineProvider.js.map