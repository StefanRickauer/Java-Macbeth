package com.rickauer.macbeth.abstractsyntaxtrees;

public interface Visitor {
	// TODO: Implement remaining logic
	
	public abstract Object visitProgram(Program ast, Object object);
	public abstract Object visitSequentialCommand(SequentialCommand ast, Object object);
	public abstract Object visitIdentifier(Identifier ast, Object object);
	public abstract Object visitCallCommand(CallCommand ast, Object object);
	public abstract Object visitAssignCommand(AssignCommand ast, Object object);
	public abstract Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object object);
	public abstract Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object object);
	public abstract Object visitConstantActualParameter(ConstantActualParameter ast, Object object);
	public abstract Object visitEmptyCommand(EmptyCommand ast, Object object);
	public abstract Object visitIntegerExpression(IntegerExpression ast, Object object);
	public abstract Object visitIntegerLiteral(IntegerLiteral ast, Object object);
	public abstract Object visitOperator(Operator ast, Object object);
	public abstract Object visitBinaryExpression(BinaryExpression ast, Object object);
	public abstract Object visitCharacterExpression(CharacterExpression ast, Object object);
	public abstract Object visitCharacterLiteral(CharacterLiteral ast, Object object);
	public abstract Object visitCallExpression(CallExpression ast, Object object);
	public abstract Object visitUnaryExpression(UnaryExpression ast, Object object);
	public abstract Object visitVnameExpression(VnameExpression ast, Object object);
	public abstract Object visitSimpleVname(SimpleVname ast, Object object);
	public abstract Object visitSubscriptVname(SubscriptVname ast, Object object);
	public abstract Object visitNumberDeclaration(NumberDeclaration ast, Object object);
	public abstract Object visitStringDeclaration(StringDeclaration ast, Object object);
	public abstract Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object object);
	public abstract Object visitTypeDeclaration(TypeDeclaration ast, Object object);
	public abstract Object visitProcDeclaration(ProcDeclaration ast, Object object);
	public abstract Object visitProcFormalParameter(ProcFormalParameter ast, Object object);
	public abstract Object visitConstantFormalParameter(ConstantFormalParameter ast, Object object);
	public abstract Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object object);
	public abstract Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object object);
}
