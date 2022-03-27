{
	"$schema": "https://raw.githubusercontent.com/martinring/tmlanguage/master/tmlanguage.json",
	"name": "tyranoscript",
	"patterns": [
		{
			"include": "#tyrano_variables"
		},
		{
			"include": "#iscript1"
		},
		{
			"include": "#iscript2"
		},			
		{
			"include": "#comment_line"
		},
		{
			"include": "#comment_block"
		},
		{
			"include": "#tyrano_labels"
		},		
		{
			"include": "#tyrano_sharp"
		},
		{
			"include": "#tyrano_tag1"
		},
		{
			"include": "#tyrano_tag2"
		}
	],
	"repository": {
		"iscript1":{
			"begin":"\\s*(\\[)((?i:iscript))(\\])",
			"beginCaptures": {
				"1":{
					"name":"keyword.operator.tyrano"
				},
				"2":{
					"name":"support.class.tyrano"
				},
				"3":{
					"name":"keyword.operator.tyrano"
				}
			},
			"end": "\\s*(\\[)((?i:endscript))\\s*((\\S*)=(\\S*))*(\\])",
			"endCaptures": {
				"1":{
					"name":"keyword.operator.tyrano"
				},
				"2":{
					"name":"support.class.tyrano"
				},
				"4":{
					"name":"support.function.tyrano"
				},
				"5":{
					"name":"string.quoted.double.tyrano"
				},
				"6":{
					"name":"keyword.operator.tyrano"
				}
			},
			"patterns":[
				{
					"include": "source.js"
				}
			]
		},
		"iscript2":{
			"begin":"(@)((?i:iscript))",
			"beginCaptures": {
				"1":{
					"name":"keyword.operator.tyrano"
				},
				"2":{
					"name":"support.class.tyrano"
				}
			},
			"end": "(@)((?i:endscript))\\s*((\\S*)=(\\S*))*",
			"endCaptures": {
				"1":{
					"name":"keyword.operator.tyrano"
				},
				"2":{
					"name":"support.class.tyrano"
				},
				"4":{
					"name":"support.function.tyrano"
				},
				"5":{
					"name":"string.quoted.double.tyrano"
				}
			},
			"patterns":[
				{
					"include": "source.js"
				}
			]
		},

		"comment_line":{
			"name": "comment.line.tyrano",
			"match": "^;.*"
		},
		"comment_block":{
			"name": "comment.block.tyrano",
			"begin":"\\/\\*",
			"end":"\\*\\/"
		},

		"tyrano_labels":{
			"name":"constant.language.tyrano",
			"comment":"^\\*.*",
			"match": "^\\*[\\w\\-＠]+"
		},
		"tyrano_sharp":{
			"name":"token.warn-token.tyrano",
			"comment": "#chara_nameはptextタグの糖衣構文なのでsupport.class.tyranoとおなじのほうがいい？",
			"match":"^#.*"
		},
		"tyrano_variables":{
			"name":"variable.parameter.tyrano",
			"comment": "変数の正規表現。変数名には半角英数と全角文字、_ (アンダーバー) を使うことができる　先頭に数字はNG",
			"comment2":"否定記号を使えば先頭数字を含まない任意の文字列、とかできるはず。",
			"match_tmp":"\\b(f\\.|sf\\.|tf\\.)([a-zA-Z_ぁ-んァ-ヶ一-龠Ａ-Ｚａ-ｚ]+)(([0-9a-zA-Z_ぁ-んァ-ヶ一-龠０-９Ａ-Ｚａ-ｚー]*))\\b",
			"match":"\\b(f\\.|sf\\.|tf\\.)([^0-9０-９])([\\w]*)\\b"
		},
		"tyrano_tag_param":{
			"comment": "タグの hoge=value  のところの正規表現",
			"match_tmp": "(\\S+)[=\\<\\>\\!\\|\\&]([\\S\\s]+)",
			"comment":"FIXME:↓condとかeval属性修正するなら適当すぎる!とか|を直すこと",
			"match": "(\\S+)[=\\<\\>\\!\\|]([\\S\\s]+)",
			"captures": {
				"1":{
					"comment": "黄色　タグのパラメータ名",
					"name":"string.other.tyrano",
					"patterns": [{
						"comment": "パラメータ名の正規表現のとこに変数の正規表現あればそっちを優先",
						"include":"#tyrano_variables"
					}]
				},
				"2":{
					"comment": "緑　パラメータに入れる値",
					"name":"entity.other.attribute-name.tyrano"
				}
			}
		},

		"tyrano_tag1":{
			"comment": "タグのハイライト []で囲むパターン",
			"begin": "\\[",
			"end": "\\]",
			"beginCaptures": {
				"1":{
					"comment":"[記号",
					"name":"keyword.operator.tyrano"					
				}
			},
			"endCaptures": {
				"1":{
					"comment":"]記号",
					"name":"keyword.operator.tyrano"				
				}
			},
			"patterns": [{
				"comment":"hoge",
				"match": "(\\w+){1}\\s*([^\\]]*)",
				"captures": {
					"1":{
						"comment": "青色　タグ名",
						"name":"support.class.tyrano"
					},				
					"2":{
						"comment":"param=value　のところ",
						"patterns": [{
							"include": "#tyrano_tag_param"
						}]
					}
	
				}
			}],
			"match_tmp": "(\\[)(\\w+){1}\\s*(.*)(\\])"
		},
		

		"tyrano_tag2":{
			"comment": "タグのハイライト 行頭が@のパターン",
			"match_tmp": "(^@)(\\w+){1}\\s*(.*)(\\n)",
			"match": "(^[\\s]*@)(\\w+){1}\\s*(.*)(\\n)",
			"captures": {
				"1":{
					"comment":"@記号",
					"name":"keyword.operator.tyrano"
				},
				"2":{
					"comment": "青色　タグ名",
					"name":"support.class.tyrano"
				},				
				"3":{
					"comment":"param=value　のところ",
					"patterns": [{
						"include": "#tyrano_tag_param"
					}]
				}
			}
		}
	},
	"scopeName": "source.ks"
}

