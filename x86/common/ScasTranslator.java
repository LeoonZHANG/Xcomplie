package rreil.disassembler.translators.x86.common;

import java.util.Arrays;
import java.util.List;

import rreil.disassembler.Instruction;
import rreil.disassembler.translators.common.InsnEmitter;
import rreil.disassembler.translators.common.InsnTranslator;
import rreil.disassembler.translators.common.RegisterTranslator;
import rreil.disassembler.translators.common.TranslationCtx;
import rreil.lang.lowlevel.OperandSize;
import rreil.lang.lowlevel.LowLevelRReil;
import rreil.lang.lowlevel.LowLevelRReilFactory;
import rreil.lang.lowlevel.LowLevelRReilOpnd;

public class ScasTranslator implements InsnTranslator {
  private static LowLevelRReilFactory factory = LowLevelRReilFactory.getInstance();

  private static final InsnEmitter emitter = new X86RmRmTranslator.SubEmitter();
  private final OperandSize elementSize;

  public ScasTranslator (OperandSize elementSize) {
    this.elementSize = elementSize;
  }

  @Override
  public void translate (final TranslationCtx env, final Instruction instruction, final List<LowLevelRReil> instructions) {
    env.setCurrentInstruction(instruction);
    final RegisterTranslator registerTranslator = env.getRegisterTranslator();

    final LowLevelRReilOpnd di = registerTranslator.translateRegister("edi").withSize(env.getDefaultArchitectureSize());
    final LowLevelRReilOpnd ax = registerTranslator.translateRegister("eax").withSize(elementSize);
    final LowLevelRReilOpnd idx = factory.immediate(di.size(), elementSize.getSizeInBytes());
    final LowLevelRReilOpnd src = registerTranslator.temporaryRegister(env, elementSize);
    final LowLevelRReilOpnd tmp = registerTranslator.temporaryRegister(env, elementSize);

    instructions.add(factory.LOAD(env.getNextReilAddress(), src, di));

    // Emit subtraction and flags
    emitter.emit(env, tmp, ax, src, instructions);

    final long base = env.getBaseAddress();
    final long reilBase = env.getCurrentReilOffset();

    final long doDecrement = 3L + reilBase;
    final long done = 1L + doDecrement;

    instructions.addAll(Arrays.asList(
        factory.IFGOTORREIL(env.getNextReilAddress(), X86Helpers.DIRECTION_FLAG_OPERAND, base, doDecrement),
        // DF==0 => si++
        factory.ADD(env.getNextReilAddress(), di, di, idx),
        factory.GOTORREIL(env.getNextReilAddress(), base, done),
        // DF==1 => si--
        factory.SUB(env.getNextReilAddress(), di, di, idx),
        // Done
        factory.NOP(env.getNextReilAddress())));
  }
}
