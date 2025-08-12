package com.cosmolego527.create_pp.item.logistics.functions;

import com.simibubi.create.content.trains.schedule.ScheduleEntry;
import com.simibubi.create.content.trains.schedule.condition.ScheduleWaitCondition;
import com.simibubi.create.content.trains.schedule.destination.ScheduleInstruction;
import net.createmod.catnip.codecs.stream.CatnipStreamCodecBuilders;
import net.createmod.catnip.nbt.NBTHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.ArrayList;
import java.util.List;

public class FunctionEntry {
    //public static final StreamCodec<RegistryFriendlyByteBuf, FunctionEntry> STREAM_CODEC = StreamCodec.composite(
    //        FunctionInstruction.STREAM_CODEC, entry -> entry.instruction,
    //        CatnipStreamCodecBuilders.list(CatnipStreamCodecBuilders.list(ScheduleWaitCondition.STREAM_CODEC)), entry -> entry.conditions,
    //        FunctionEntry::new
    //);

    public FunctionInstruction instruction;
    public List<List<ScheduleWaitCondition>> conditions;

    public FunctionEntry() {
        conditions = new ArrayList<>();
    }

    public FunctionEntry(FunctionInstruction instruction, List<List<ScheduleWaitCondition>> conditions) {
        this.instruction = instruction;
        this.conditions = conditions;
    }

    public FunctionEntry clone(HolderLookup.Provider registries) {
        return fromTag(registries, write(registries));
    }

    public CompoundTag write(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        ListTag outer = new ListTag();
        tag.put("Instruction", instruction.write(registries));
        if (!instruction.supportsConditions())
            return tag;
        for (List<ScheduleWaitCondition> column : conditions)
            outer.add(NBTHelper.writeCompoundList(column, t -> t.write(registries)));
        tag.put("Conditions", outer);
        return tag;
    }

    public static FunctionEntry fromTag(HolderLookup.Provider registries, CompoundTag tag) {
        FunctionEntry entry = new FunctionEntry();
        entry.instruction = FunctionInstruction.fromTag(registries, tag.getCompound("Instruction"));
        entry.conditions = new ArrayList<>();
        if (entry.instruction.supportsConditions())
            for (Tag t : tag.getList("Conditions", Tag.TAG_LIST))
                if (t instanceof ListTag list)
                    entry.conditions.add(NBTHelper.readCompoundList(list, conditionTag -> ScheduleWaitCondition.fromTag(registries, conditionTag)));
        return entry;
    }

}
