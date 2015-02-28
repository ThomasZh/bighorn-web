// GoJudgement.js
// thomas.h.zhang
// 2008-06-28

// ��Ķ���
function GoJudgement(boardSize, stoneArray) {
	this.BoardSize = boardSize; // ���̴�С, Ĭ��Ϊ19*19
	this.KoI = 19; // ���ý�����Ϊ(19,19), ���˵�Ŀǰ������
	this.KoJ = 19;

	this.StoneArray = stoneArray; // �����ϵ���������: 1 black, -1 white, 0 space
	this.LibertyArray = new Array(); // �����ϵ�����ӵ�е�������: 0,1,2,3,4
	this.LibertyFlagArray = new Array(); // ���Ѿ�������ϣ������ظ����������
	this.AroundCountArray = new Array(); // ĳ������Χ���Ӹ���, ��Ե����ɫ++
	this.SelfPonnukiArray = new Array(); // ��ʱ�洢���ӵ�����
	this.PonnukiArray = new Array(); // �洢���ӵ�����
};

// initializtion
GoJudgement.prototype.Init = function() {
	for ( var i = 0; i < this.BoardSize; i++) {
		// ��ʼ�������ϵ���������
		this.LibertyArray[i] = new Array();
		this.LibertyFlagArray[i] = new Array();
		this.AroundCountArray[i] = new Array();
		this.PonnukiArray[i] = new Array();
		this.SelfPonnukiArray[i] = new Array();

		for ( var j = 0; j < this.BoardSize; j++) {
			// ��ʼ�������ϵ��������� 0=null
			this.LibertyArray[i][j] = 0;
			this.LibertyFlagArray[i][j] = false;
			this.AroundCountArray[i][j] = 0;
			this.PonnukiArray[i][j] = false;
			this.SelfPonnukiArray[i][j] = false;
		}
	}
};

// �����ʱ�����б�
GoJudgement.prototype.CleanSelfLiberty = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			this.SelfPonnukiArray[i][j] = false;
};

// ��������б�
GoJudgement.prototype.CleanLiberty = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			this.PonnukiArray[i][j] = false;
};

// ���ĳ������Χ���Ӹ���
GoJudgement.prototype.CleanAroundCount = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			this.AroundCountArray[i][j] = 0;
};

// ���ĳ�������Ѿ�������ϱ�־
GoJudgement.prototype.CleanLibertyFlag = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			this.LibertyFlagArray[i][j] = false;
};

// ����ʱ�����б�׷���������б�
GoJudgement.prototype.AppendLiberty = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			if (this.SelfPonnukiArray[i][j] == true)
				this.PonnukiArray[i][j] = true;
};

// ����ĳ����Χ����
GoJudgement.prototype.CountLiberty = function(i, j) {
	var result = 0;

	// (i-1,j)���������� && û����
	if ((i - 1) >= 0 && this.GetColor(i - 1, j) == 0)
		result++;
	// (i+1,j)���������� && û����
	if ((i + 1) < this.BoardSize && this.GetColor(i + 1, j) == 0)
		result++;
	// (i,j-1)���������� && û����
	if ((j - 1) >= 0 && this.GetColor(i, j - 1) == 0)
		result++;
	// (i,j+1)���������� && û����
	if ((j + 1) < this.BoardSize && this.GetColor(i, j + 1) == 0)
		result++;

	return result;
};

// ���������������ӵ���, ��������LibertyArray��
GoJudgement.prototype.CountAllLiberty = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			this.LibertyArray[i][j] = this.CountLiberty(i, j);
};

// ���ý�����
GoJudgement.prototype.SetKo = function(i, j) {
	this.KoI = i;
	this.KoJ = j;
};

// ȡ�ý���������i
GoJudgement.prototype.GetKoI = function() {
	return this.KoI;
};

// ȡ�ý���������j
GoJudgement.prototype.GetKoJ = function() {
	return this.KoJ;
};

// ȡ��(i,j)���괦���ӵ���ɫ: 1 black, -1 white, 0 space
GoJudgement.prototype.GetColor = function(i, j) {
	return this.StoneArray[i][j];
};

// ����(i,j)���괦���ӵ���ɫ: 1 black, -1 white, 0 space
GoJudgement.prototype.SetColor = function(i, j, color) {
	this.StoneArray[i][j] = color;
};

// ����������
// param: ��ʼpoint(i,j)
// return: 0 û������, n ���Ӹ���
// �ݹ麯��
// ���ĺ���, GoGameObject��ľ�������
GoJudgement.prototype.CountSelfPonnuki = function(i, j) {
	var result = 0; // ������
	var count = 0;

	// ��һ���������Ǵ���û����
	if (this.LibertyArray[i][j] != 0)
		return result; // ����

	// ��Ǵ����Ѿ�������ϣ������ظ����������
	this.LibertyFlagArray[i][j] = true;

	// (i-1,j)
	if ((i - 1) < 0) // ����
		this.AroundCountArray[i][j]++;
	else {
		if ((this.GetColor(i, j) + this.GetColor(i - 1, j)) == 0) // ��ɫ
			this.AroundCountArray[i][j]++;
		else { // same color
			if (this.LibertyFlagArray[i - 1][j] == true) // �������
				this.AroundCountArray[i][j]++;
			else { // δ����
				// (i-1,j)������
				if ((count = this.CountSelfPonnuki(i - 1, j)) == 0) {
					this.SelfPonnukiArray[i][j] = false;
					return (count);
				} else { // (i-1,j)������
					this.AroundCountArray[i][j]++;
					result += count;
				}
			}
		}
	}

	// (i+1,j)
	if ((i + 1) > this.BoardSize - 1) // ����
		this.AroundCountArray[i][j]++;
	else {
		if ((this.GetColor(i, j) + this.GetColor(i + 1, j)) == 0) // ��ɫ
			this.AroundCountArray[i][j]++;
		else { // same color
			if (this.LibertyFlagArray[i + 1][j] == true) // �������
				this.AroundCountArray[i][j]++;
			else { // δ����
				// (i+1,j)������
				if ((count = this.CountSelfPonnuki(i + 1, j)) == 0) {
					this.SelfPonnukiArray[i][j] = false;
					return (count);
				} else { // (i+1,j)������
					this.AroundCountArray[i][j]++;
					result += count;
				}
			}
		}
	}

	// (i,j-1)
	if ((j - 1) < 0) // ����
		this.AroundCountArray[i][j]++;
	else {
		if ((this.GetColor(i, j) + this.GetColor(i, j - 1)) == 0) // ��ɫ
			this.AroundCountArray[i][j]++;
		else { // same color
			if (this.LibertyFlagArray[i][j - 1] == true) // �������
				this.AroundCountArray[i][j]++;
			else { // δ����
				// (i,j-1)������
				if ((count = this.CountSelfPonnuki(i, j - 1)) == 0) {
					this.SelfPonnukiArray[i][j] = false;
					return (count);
				} else { // (i,j-1)������
					this.AroundCountArray[i][j]++;
					result += count;
				}
			}
		}
	}

	// (i,j+1)
	if ((j + 1) > this.BoardSize - 1) // ����
		this.AroundCountArray[i][j]++;
	else {
		if ((this.GetColor(i, j) + this.GetColor(i, j + 1)) == 0) // ��ɫ
			this.AroundCountArray[i][j]++;
		else { // color
			if (this.LibertyFlagArray[i][j + 1] == true) // �������
				this.AroundCountArray[i][j]++;
			else { // δ����
				// (i,j+1)������
				if ((count = this.CountSelfPonnuki(i, j + 1)) == 0) {
					this.SelfPonnukiArray[i][j] = false;
					return (count);
				} else { // (i,j+1)������
					this.AroundCountArray[i][j]++;
					result += count;
				}
			}
		}
	}

	// �������������²���
	if (this.AroundCountArray[i][j] == 4) {
		this.SelfPonnukiArray[i][j] = true;
		// alert("i:"+i+",j:"+j+",AroundCount:"+__AroundCountArray[i][j]);
		result++;
		return (result);
	}

	return result;
};

// ȡ�������Ӹ���
GoJudgement.prototype.CountSelfPonnukiNum = function(i, j) {
	this.CountAllLiberty();
	this.CleanAroundCount();
	this.CleanLibertyFlag();
	this.CleanSelfLiberty();
	return this.CountSelfPonnuki(i, j);
};

// ������Χ����
// �����˺��ĺ���
GoJudgement.prototype.CountPonnuki = function(i, j) {
	var result = 0;
	var count = 0;
	this.CountAllLiberty();
	this.CleanLiberty();

	if ((i - 1) >= 0) // δ����
		if (this.GetColor(i, j) + this.GetColor(i - 1, j) == 0) // ��ɫ
		{
			this.CleanAroundCount();
			this.CleanLibertyFlag();
			this.CleanSelfLiberty();
			count = this.CountSelfPonnuki(i - 1, j);
			if (count > 0)
				this.AppendLiberty();
			result += count;
		}

	if ((i + 1) < this.BoardSize) // δ����
		if (this.GetColor(i, j) + this.GetColor(i + 1, j) == 0) // ��ɫ
		{
			this.CleanAroundCount();
			this.CleanLibertyFlag();
			this.CleanSelfLiberty();
			count = this.CountSelfPonnuki(i + 1, j);
			if (count > 0)
				this.AppendLiberty();
			result += count;
		}

	if ((j - 1) >= 0) // δ����
		if (this.GetColor(i, j) + this.GetColor(i, j - 1) == 0) // ��ɫ
		{
			this.CleanAroundCount();
			this.CleanLibertyFlag();
			this.CleanSelfLiberty();
			count = this.CountSelfPonnuki(i, j - 1);
			if (count > 0)
				this.AppendLiberty();
			result += count;
		}

	if ((j + 1) < this.BoardSize) // δ����
		if (this.GetColor(i, j) + this.GetColor(i, j + 1) == 0) // ��ɫ
		{
			this.CleanAroundCount();
			this.CleanLibertyFlag();
			this.CleanSelfLiberty();
			count = this.CountSelfPonnuki(i, j + 1);
			if (count > 0)
				this.AppendLiberty();
			result += count;
		}

	return result;
};

// ��ӡ�����б�
GoJudgement.prototype.PrintPonnukiArray = function() {
	for ( var i = 0; i < this.BoardSize; i++)
		for ( var j = 0; j < this.BoardSize; j++)
			if (this.PonnukiArray[i][j] == true)
				alert("i:" + i + ",j:" + j + " is ponnuki");
};

// ȡ����ʱ�����б�
GoJudgement.prototype.GetSelfPonnukiArry = function() {
	return this.SelfPonnukiArray;
};

// ȡ�������б�
GoJudgement.prototype.GetPonnukiArray = function() {
	return this.PonnukiArray;
};
